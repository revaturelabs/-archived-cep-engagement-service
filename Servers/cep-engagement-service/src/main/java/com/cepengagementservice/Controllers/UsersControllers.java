package com.cepengagementservice.Controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cepengagementservice.Models.Request;
import com.cepengagementservice.Models.User;
import com.cepengagementservice.Models.UserProfile;
import com.cepengagementservice.Services.RequestService;
import com.cepengagementservice.Services.UserServices;

/**
 * Handles request from the front ends including getting all the users adding a
 * user finding user by email or id ADMINs can view, create and delete
 * intervientions
 * 
 * @author Unknown
 *
 */
@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UsersControllers {

	@Value("${jwt.http.request.header}") // grabs header from src/main/resources/app.properties
	private String tokenHeader;
	
	@Value("${key.allemail}") // grabs header from src/main/resources/app.properties
	private String emailKey;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserServices userService;

	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public ResponseEntity<List<User>> getAll() {
		List<User> users = userService.getAllUsers();
		if (users != null) {
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.NO_CONTENT);
	}

	// Future, maybe return user.
	// Change logic in service.
	/**
	 * Add a user and hashes the passwords
	 * 
	 * @param User user
	 * @return ResponseEntity<?>(<?> Extends object)
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public ResponseEntity<?> add(@RequestBody User user) {
		// Encoding password
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		if (userService.addUser(user)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<String>("Email already in use", HttpStatus.CONFLICT);
	}

	// DO SOME ERROR CHECKING.
	// @RequestMapping(method = RequestMethod.POST, value = "/add/batch")
	// ResponseEntity<?> add(@RequestBody Batch batch, @RequestParam Integer id) {
	// User usr = userService.getUserById(id);
	// if (usr != null) {
	// usr.addBatch(batch);
	// usr = userService.updateUser(usr);
	// return new ResponseEntity<User>(usr, HttpStatus.ACCEPTED);

	// }
	// return new ResponseEntity<User>(usr, HttpStatus.NOT_FOUND);

	// }

	@RequestMapping(method = RequestMethod.GET, value = "/email/")
	public ResponseEntity<User> getByEmail(@RequestParam String email) {
		User user = userService.getUserByEmail(email);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/email/all")
	public ResponseEntity<List<String>> getAllEmail(HttpServletRequest request) {
		String authKey = request.getHeader(tokenHeader);
		try {
			if (authKey.equals(emailKey)) {
				List<String> emails = userService.allEmail();
				return new ResponseEntity<List<String>>(emails, HttpStatus.OK);
			}
			return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/email/admin")
	public ResponseEntity<List<String>> getAdminEmail(HttpServletRequest request) {
		String authKey = request.getHeader(tokenHeader);
		try {
			if (authKey.equals(emailKey)) {
				List<String> emails = userService.adminEmail();
				return new ResponseEntity<List<String>>(emails, HttpStatus.OK);
			}
			return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/")
	public ResponseEntity<User> getById(@RequestParam Integer id) {
		User user = userService.getUserById(id);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
	}

	/**
	 * This method checks that the new password was correctly confirmed in the Reset Password model and then uses JwtToken to determine which user is currently trying to reset their password
	 * We then set the password using the user service
	 * @param reset model that contains the old and new password as well as the confirmation of the new password
	 * @param request Http request object
	 * @return Response entity with a string that will notify if the passwords do not match otherwise just a success
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/resetpassword")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPassword reset, HttpServletRequest request) {

		User user = userService.getUserByEmail(reset.getEmail());
		
		System.out.println(user.getPassword());
		
		
		if(!BCrypt.checkpw(reset.getOldPassword(), user.getPassword())) {
			return new ResponseEntity<String>("Old password entered is not valid", HttpStatus.BAD_REQUEST);
		}
		
		if(!reset.getConfirmedPassword().equals(reset.getNewPassword())) {
			return new ResponseEntity<String>("Passwords do not match", HttpStatus.BAD_REQUEST);
		}
		
		user.setPassword(bCryptPasswordEncoder.encode(reset.getConfirmedPassword()));
		user.setResetPassword(false);
		userService.updateUser(user);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// @RequestMapping(method = RequestMethod.GET, value = "/batch/")
	// public ResponseEntity<?> getByBatchId(@RequestParam String batchId) {
	// List<User> user = userService.getByBatchId(batchId);
	// if (user != null) {
	// return new ResponseEntity<List<User>>(user, HttpStatus.OK);
	// }
	// return new ResponseEntity<List<User>>(user, HttpStatus.NO_CONTENT);
	// }

	////////////////// ADMIN ROLES /////////////////////
	// ADMINs can update and delete the request status
	@Autowired
	RequestService requestService;

	@GetMapping("/admin/request")
	public List<Request> getAllInterventions() {
		return requestService.findAll();
	}

	/**
	 * Returns a Request model by its Id
	 * 
	 * @param int     requestId
	 * @param request
	 * @return
	 */
	@PutMapping("/admin/request/update/{requestId}")
	public ResponseEntity<?> updateRequest(@PathVariable("requestId") int requestId, @RequestBody Request request) {

		Request currentRequest = requestService.findByRequestId(requestId);

		if (currentRequest == null) {
			return new ResponseEntity<String>("Unable to Update. Request ID: " + requestId + " not found.",
					HttpStatus.NOT_FOUND);
		}

		currentRequest.setStatus(request.getStatus());
		requestService.updateRequest(currentRequest);
		return new ResponseEntity<Request>(currentRequest, HttpStatus.OK);
	}

	/**
	 * Removes a Request object by its ID
	 * 
	 * @param int requestId
	 * @return
	 */
	@DeleteMapping("/admin/request/delete/{requestId}")
	public ResponseEntity<?> deleteRequest(@PathVariable("requestId") int requestId) {

		Request request = requestService.findByRequestId(requestId);

		if (request == null) {
			return new ResponseEntity<String>("Unable to Delete. Request ID: " + requestId + " not found.",
					HttpStatus.NOT_FOUND);
		}
		requestService.deleteByRequestId(requestId);
		return new ResponseEntity<Request>(HttpStatus.NO_CONTENT);

	}
	
	/**
	 * Retrieves a UserProfile object using the profileDeadline, profileCount, and User-Category pairs of the given user
	 * @param userId
	 * @return userProfile
	 */
	@GetMapping("/profile/{userId}")
	public ResponseEntity<?> getUserProfile(@PathVariable("userId") int userId) {
		UserProfile targetProfile = userService.getProfileById(userId);
		
		return new ResponseEntity<UserProfile>(targetProfile, HttpStatus.OK);
	}
	
	@PutMapping("/profile/{userId}")
	public ResponseEntity<String> updateUserProfile(@PathVariable("userId") int userId, @RequestBody UserProfile newProfile) {		
		//Save new preferences to user object and User-Category objects
		boolean didUpdate = userService.updateUserProfile(userId, newProfile);
		
		if (didUpdate) {
			return new ResponseEntity<String>("User profile updated", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Matching user not found.", HttpStatus.NOT_FOUND);
		}
	}

}
