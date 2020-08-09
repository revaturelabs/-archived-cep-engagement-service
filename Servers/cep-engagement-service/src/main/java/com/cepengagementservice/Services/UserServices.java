package com.cepengagementservice.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cepengagementservice.Models.Category;
import com.cepengagementservice.Models.User;
import com.cepengagementservice.Models.UserCategory;
import com.cepengagementservice.Models.UserProfile;
import com.cepengagementservice.Projections.UserProfileProjection;
import com.cepengagementservice.Repositories.UserRepository;

/**
 * 
 * @author Unknown this is supposed to utilize the userRepository in order to
 *         access and store all the clients, admins, and other potential users
 *
 */
@Service
@Primary

public class UserServices {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserCategoryService ucService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserBatchService userBatchService;

	/**
	 * this is to get all registered users
	 * 
	 * @return an arraylist with all users contained within
	 */
	@Cacheable("GetAllUsers")
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	/**
	 * this is a function used to primarily store a new user. Has a if function
	 * within to check whether or not the user already exists, and saves only if the
	 * user has not been previously registered by the email address
	 * 
	 * @param user is a user object
	 * @return a boolean to indicate whether the save was successful
	 */
	// Better way instead of true false?
	public Boolean addUser(User user) {
		if (userRepository.findByEmail(user.getEmail()) != null) {
			return false;
		}
		userRepository.save(user);
		return true;
	}

	/**
	 * Check to see if the user exists (Note from Miki: this is redundant. The
	 * previous function is the same except it integrates the functionality of save
	 * which is an evolution from this, and so I would suggest removing it)
	 * 
	 * @param userId
	 * @return boolean
	 */
	public boolean check(int userId) {
		if (this.getUserById(userId) != null)
			return true; // there is a user
		else
			return false;
	}

	/**
	 * basic method to find by email
	 * 
	 * @param email
	 * @return a user
	 */
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/**
	 * this is a method to find the user by their ID number
	 * 
	 * @param id
	 * @return a user or null
	 */
	// Either do Optional<Users> or check yourself.
	@Cacheable("GetUserById")
	public User getUserById(Integer id) {
		return userRepository.findById(id).get();
	}

	// public List<User> getByBatchId(String batchId){
	// return userRepository.findByBatchId(batchId);
	// }
	/**
	 * this is a method to update the user
	 * NOTE: Right now, this does NOT update the user's User-Category pairs or User-Batch pairs.
	 * 
	 * @param user
	 * @return the user repository with the new user having been saved(updated)
	 */
	public User updateUser(User user) {
		
		
		return userRepository.save(user);
	}

	public List<String> allEmail() {
		return userRepository.getAllEmail();
	}

	public List<String> adminEmail() {
		return userRepository.getAdminEmail();
	}

	/**
	 * Retrieves the info relevant to the user's batch preferences, including the
	 * user's profileDeadline and profileCount fields, and a list of Categories that
	 * the users has indicated are necessary skills.
	 * 
	 * @param userId
	 * @return
	 */
	public UserProfile getProfileById(int userId) {
		UserProfileProjection targetProfileFields = userRepository.findByUserId(userId, UserProfileProjection.class);
		List<UserCategory> targetUCPairs = ucService.getUCsByUserId(userId);

		// This will call getCategoryId in every object in targetUCPairs to create a new
		// list of the categoryIds
		if (!targetUCPairs.isEmpty()) {
			List<Integer> targetUCIdsIntegers = targetUCPairs.stream().map(UserCategory::getCategoryId)
					.collect(Collectors.toList());

			int[] targetUCIdsInts = targetUCIdsIntegers.stream().mapToInt(Integer::intValue).toArray();

			List<Category> targetCategories = categoryService.getCategoriesByIds(targetUCIdsInts);
			return new UserProfile(targetProfileFields.getProfileDeadline(), targetProfileFields.getProfileCount(),
					targetCategories);
		} else {
			return new UserProfile(targetProfileFields.getProfileDeadline(), targetProfileFields.getProfileCount(),
					new ArrayList<Category>());
		}

	}

	/**
	 * Updates the user's preferences, including deleting all of the user's UC pairs
	 * and generating new ones based on the new profile's neededCategories
	 * OPPORTUNITY FOR OPTIMIZATION: compare the new and old UC pairs to only delete
	 * the ones that need to be delete and then only add the ones that need to be
	 * added. NOTE: Does not automatically update the user's User-Batch pairs with
	 * new preferences; call UB/refresh after using this endpoint to do that
	 * 
	 * @param userId
	 * @param newProfile
	 */
//	@Transactional
	public boolean updateUserProfile(int userId, UserProfile newProfile) {
		User targetUser = userRepository.findByUserId(userId);

		if (targetUser == null) {
			return false;
		} else {

			targetUser.setProfileDeadline(newProfile.getBatchDeadline());
			targetUser.setProfileCount(newProfile.getAssociateCount());

			userRepository.save(targetUser);

			ucService.deleteUCsByUserId(userId);
			ucService.addUCsAsGroup(userId, newProfile.getNeededCategories());
			
			userBatchService.refreshUserBatchesForUser(userId);

			return true;
		}

	}

}