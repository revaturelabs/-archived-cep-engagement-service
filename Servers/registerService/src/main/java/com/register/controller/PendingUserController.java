package com.register.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.register.model.Email;
import com.register.model.PendingUser;
import com.register.model.RegisterInfo;
import com.register.service.PendingUserServiceImpl;

/**
 * Need to test all the methods
 * H2 Needs to be setup
 * Proper paths from cep-engagement need to be set
 * @author
 *
 */
@RestController
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
	 * 	Needs testing
	 * @return List of all PendingUser where status = "Pending"
	 */
	@GetMapping("/pending/all")
	public ResponseEntity<List<PendingUser>> allUsers() {
		try {
			List<PendingUser> users = pendingUserService.allPendingUsers();
			return new ResponseEntity<List<PendingUser>> (users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<PendingUser>> (HttpStatus.BAD_REQUEST);
		}
		
	}
	

	/**
	 * Needs testing
	 * @param user
	 * @return
	 */
	@PostMapping("/pending/add")
	public ResponseEntity<String> addUser(@RequestBody PendingUser user) {
		try {
			//Rest Template is used to verify email is unique by querying DB in cep-service
			RestTemplate rest = new RestTemplate();
			Email email = rest.getForObject("http://localhost:9015/cep-engagement-service/users/email/?email={email}", Email.class, user.getEmail());
			if (email != null) {
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
	 * Needs testing
	 * @param id of user being approved
	 * @return user object
	 */
	@GetMapping("/pending/approve")
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
	 * Needs Testing
	 *  Deletes PendingUser from table
	 * @param id of user to be denied
	 * @return string "Success" if works
	 */
	@GetMapping("/pending/deny")
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
	 * Needs to test
	 * @param password
	 * @return
	 */
	@PostMapping("/pending/register")
	public ResponseEntity<String> registerUser(@RequestBody RegisterInfo register ){
		try {
			PendingUser user = pendingUserService.findByEmail(register.getEmail());
			user.setPassword(register.getPassword());
			RestTemplate rest = new RestTemplate();
			rest.postForObject("localhost:9015/cep-engagement-service/users/add", user, null);
			return new ResponseEntity<String> ("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> (HttpStatus.BAD_REQUEST);
		}
	}
}
