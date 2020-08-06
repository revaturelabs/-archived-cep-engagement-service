package com.register.controller;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.register.model.DenyMessage;
import com.register.model.PendingUser;
import com.register.model.PendingUserSend;
import com.register.service.PendingUserServiceImpl;
import com.register.util.EmailSender;

/**
 * 
 * H2 Needs to be setup
 * Proper paths from cep-engagement need to be set
 * @author
 *
 */
@RestController
@RequestMapping("/pending")
@CrossOrigin(origins="*")
public class PendingUserController {

	//Field
	public PendingUserServiceImpl pendingUserService;
	
	@Value("${key.allemail}") // grabs value from src/main/resources/app.properties
	private String emailKey;
	
	//Constructors
	public PendingUserController() {}
	
	@Autowired
	public PendingUserController(PendingUserServiceImpl pendingUserService) {
		super();
		this.pendingUserService = pendingUserService;
	}
	
	//Controller Methods
	
	/**
	 *
	 * @return List of all PendingUser where status = "Pending"
	 */
	@GetMapping("/all")
	public ResponseEntity<List<PendingUser>> allUsers() {
		System.out.println(0);
		try {
			System.out.println(1 + " " + pendingUserService.allPendingUsers());
			List<PendingUser> users = pendingUserService.allPendingUsers();
			System.out.println(2 + " " + users);
			return new ResponseEntity<List<PendingUser>> (users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<PendingUser>> (HttpStatus.BAD_REQUEST);
		}
		
	}
	

	/**
	 *
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	@PostMapping("/add")
	public ResponseEntity<String> addUser(@RequestBody PendingUser user) {
		System.out.println("before");
		try {
			System.out.println("inside");
			
			ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());

			//Rest Template is used to verify email is unique by querying DB in cep-service
			RestTemplate rest = new RestTemplate(factory);
			//String[] str = rest.getForObject("http://localhost:9015/users/email/all", String[].class);
			// create headers
			HttpHeaders headers = new HttpHeaders();

			// set Content-Type and Accept headers
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			// example of custom header
			headers.set("Authorization", emailKey);

			// build the request
			HttpEntity<?> request = new HttpEntity<>(headers);
			System.out.println("before request");
			// make an HTTP GET request with headers
			ResponseEntity<String[]> str = rest.exchange(
					"http://localhost:9015/users/email/all",
			        HttpMethod.GET,
			        request,
			        String[].class
			);
			String[] emails = str.getBody();
			ArrayList<String>emailList = new ArrayList<String>(Arrays.asList(emails));
			if (emailList.contains(user.getEmail()) || pendingUserService.findByEmail(user.getEmail()) != null) {
				return new ResponseEntity<String> ("Email taken", HttpStatus.BAD_REQUEST);
			}
			pendingUserService.addUser(user);
			
			//Sending a notification email to all Admins of a new pending user
			
			RestTemplate rest2 = new RestTemplate(factory);
			HttpHeaders headers2 = new HttpHeaders();

			// set Content-Type and Accept headers
			headers2.setContentType(MediaType.APPLICATION_JSON);
			headers2.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			headers2.set("Authorization", emailKey);

			HttpEntity<?> request2 = new HttpEntity<>(headers2);
			// make an HTTP GET request with headers
			ResponseEntity<String[]> str2 = rest2.exchange(
					"http://localhost:9015/users/email/admin",
			        HttpMethod.GET,
			        request2,
			        String[].class
			);
			String[] emails2 = str2.getBody();
			ArrayList<String>emailList2 = new ArrayList<String>(Arrays.asList(emails2));
			
			for(String email: emailList2) {
				EmailSender.sendAsHtml(email, "New CEP Registration", "A new client has registered for the CEP. <br/>Name: "
			+user.getFirstName()+ " "+user.getLastName()+ "<br/>Company: "+user.getCompany()+"<br/>Email: "+user.getEmail()+"<br/>Phone: "+user.getPhone());
			}
			
			
			return new ResponseEntity<String> ("Success", HttpStatus.OK);
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			return new ResponseEntity<String> (HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 *
	 * @param id of user being approved
	 * @return user object
	 */
	@GetMapping("/approve")
	public ResponseEntity<String> approveUser(@RequestParam("id") int id){
		try {
			PendingUser user = pendingUserService.findById(id);
			user.setPassword(generateRandomPassword(8));
			RestTemplate rest = new RestTemplate();
			PendingUserSend pend = new PendingUserSend(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getCompany(), user.getRole(), user.getPhone());
			rest.postForObject("http://localhost:9015/users/add", pend, String.class);
			System.out.println(user);
			pendingUserService.deleteUser(user);
			EmailSender.sendAsHtml(user.getEmail(), "Your Revature CEP account has been approved!", "Congrats, you have been approved and your password is: " + user.getPassword());
			return new ResponseEntity<String> ("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> (HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 *  Deletes PendingUser from table
	 * @param id of user to be denied
	 * @return string "Success" if works
	 */
	@PostMapping("/deny")
	public ResponseEntity<String> denyUser(@RequestParam("id") int id, @RequestBody DenyMessage denyMessage) {
		try {
			System.out.println(denyMessage);
			PendingUser user = pendingUserService.findById(id);
			pendingUserService.deleteUser(user);
			EmailSender.sendAsHtml(user.getEmail(), "Your Revature CEP account has been denied!", "Sorry, you have been denied for the following reason(s): " + denyMessage.getDenyMessage());
			return new ResponseEntity<String> ("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> (HttpStatus.BAD_REQUEST);
		}
	}
	
	public static String generateRandomPassword(int len) {
        // ASCII range - alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        // each iteration of loop choose a character randomly from the given ASCII range
        // and append it to StringBuilder instance

        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }
	
//	/**
//	 * @param password
//	 * @return
//	 */
//	@PostMapping("/register")
//	public ResponseEntity<String> registerUser(@RequestBody RegisterInfo register ){
//		try {
//			PendingUser user = pendingUserService.findByEmail(register.getEmail());
//			user.setPassword(generateRandomPassword(8));
//			RestTemplate rest = new RestTemplate();
//			rest.postForObject("http://localhost:9015/users/add", user, String.class);
//			return new ResponseEntity<String> ("Success", HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<String> (HttpStatus.BAD_REQUEST);
//		}
//	}
}
