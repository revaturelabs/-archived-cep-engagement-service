package com.register.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.register.model.PendingUser;
import com.register.service.PendingUserService;
import com.register.service.PendingUserServiceImpl;
/**
 * We need to implement the all, and approve controller layers.
 * Need to understand how ResponseEntity works cause it is giving us issues
 * @author Ben
 *
 */
@RestController
public class PendingUserController {

	public PendingUserService pendingUserService = new PendingUserServiceImpl();
	
	/**
	 * 	
	 * @return all users
	 */
	@GetMapping("/all")
	public ResponseEntity<List<PendingUser>> allUsers() {
		try {
			List<PendingUser> users = pendingUserService.allUsers();
			return new ResponseEntity<List<PendingUser>> (users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<PendingUser>> (null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addUser(@RequestBody PendingUser user) {
		try {
			pendingUserService.addUser(user);
			return new ResponseEntity<String> ("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> ("Failure", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/approve")
	public ResponseEntity<PendingUser> approveUser()
}
