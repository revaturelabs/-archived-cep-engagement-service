package com.register.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.register.model.PendingUser;
import com.register.model.RegisterInfo;
import com.register.service.PendingUserServiceImpl;

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
		try {
			List<PendingUser> users = pendingUserService.allPendingUsers();
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
		try {
			//Rest Template is used to verify email is unique by querying DB in cep-service
			RestTemplate rest = new RestTemplate();
			//String[] str = rest.getForObject("http://localhost:9015/users/email/all", String[].class);
			// create headers
			HttpHeaders headers = new HttpHeaders();

			// set Content-Type and Accept headers
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			// example of custom header
			headers.set("Authorization", "pass");

			// build the request
			HttpEntity<?> request = new HttpEntity<>(headers);

			// make an HTTP GET request with headers
			ResponseEntity<String[]> str = rest.exchange(
					"http://localhost:9015/users/email/all",
			        HttpMethod.GET,
			        request,
			        String[].class
			);
			System.out.println(str);
			if (Arrays.asList(str).contains(user.getEmail())) {
				return new ResponseEntity<String> ("Email taken", HttpStatus.BAD_REQUEST);
			}
			pendingUserService.addUser(user);
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
			user.setStatus("Approved");
			pendingUserService.updateUser(user);
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
	@GetMapping("/deny")
	public ResponseEntity<String> denyUser(@RequestParam("id") int id) {
		try {
			PendingUser user = pendingUserService.findById(id);
			pendingUserService.deleteUser(user);
			return new ResponseEntity<String> ("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> (HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * @param password
	 * @return
	 */
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody RegisterInfo register ){
		try {
			PendingUser user = pendingUserService.findByEmail(register.getEmail());
			user.setPassword(register.getPassword());
			RestTemplate rest = new RestTemplate();
			rest.postForObject("http://localhost:9015/users/add", user, String.class);
			return new ResponseEntity<String> ("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> (HttpStatus.BAD_REQUEST);
		}
	}
}
