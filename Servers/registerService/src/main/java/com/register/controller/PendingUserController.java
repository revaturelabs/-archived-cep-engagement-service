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

import com.register.model.PendingUser;
import com.register.service.PendingUserServiceImpl;

/**
 * Controller layer for register service
 * Need to fix /approve and add /register
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
	 * Needs to implement Rest Template to query cep-engagement DB to ensure email is unique
	 * @param user
	 * @return
	 */
	@PostMapping("/add")
	public ResponseEntity<String> addUser(@RequestBody PendingUser user) {
		try {
			//Implement RestTemplate here, use the /email path on cep-engagement
			pendingUserService.addUser(user);
			return new ResponseEntity<String> ("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> (HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Needs testing
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
	 * Needs Testing
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
	 * Needs to fix
	 * @param password
	 * @return
	 */
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody ){
		try {
			//add functionality to update password, delete from PendingUser table, and RestTemplate /add
			return new ResponseEntity<String> ("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> (HttpStatus.BAD_REQUEST);
		}
	}
}
