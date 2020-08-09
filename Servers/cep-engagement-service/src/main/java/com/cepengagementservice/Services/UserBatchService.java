package com.cepengagementservice.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cepengagementservice.Models.Assessment;
import com.cepengagementservice.Models.Batch;
import com.cepengagementservice.Models.Category;
import com.cepengagementservice.Models.UserBatch;
import com.cepengagementservice.Models.UserProfile;
import com.cepengagementservice.Models.dto.BatchDTO;
import com.cepengagementservice.Repositories.UserBatchRepository;

/**
 * 
 * @author unknown this service is meant to utilize user and batch services to
 *         create the batches and associate them with users who are able to
 *         interact/see them in the application
 *
 */
@Service
public class UserBatchService {
	/**
	 * these are the services for the batch and users and the user batch repository
	 * this allows for all the functionality required
	 */
	@Autowired
	UserBatchRepository userBatchRepository;
	@Autowired
	private BatchService batchService;

	@Autowired
	private UserServices userService;

	/**
	 * these two functions return all the batches which are associated with the
	 * user. Thus we can make sure that the user only returns the batches the admins
	 * want them to see.
	 * 
	 * @param userId
	 * @return A List of Batch objects Related to the UserId
	 */
	public List<BatchDTO> getBatchesDTOByUserId(int userId) {
		List<UserBatch> ubatches = new ArrayList<>();
		try {
			ubatches = userBatchRepository.findByUserId(userId);
		} catch (Exception e) {
			// TODO:Log
			System.out.println(e);
		}
		List<BatchDTO> batches = new ArrayList<>();
		// use the list of batchId Strings to make a list of BatchesDTO
		for (UserBatch ub : ubatches) {
			batches.add(batchService.getSingleBatchDTO(ub.getBatchId()));
		}
		return batches;
	}

	/**
	 * these two functions return all the batches which are associated with the
	 * user. Thus we can make sure that the user only returns the batches the admins
	 * want them to see.
	 * 
	 * @param userId
	 * @return A List of Batch objects Related to the UserId
	 */
	public List<Batch> getBatchesByUserId(int userId) {
		List<UserBatch> ubatches = new ArrayList<>();
		try {
			ubatches = userBatchRepository.findByUserId(userId);
		} catch (Exception e) {
			System.out.println(e);
		}
		List<Batch> batches = new ArrayList<>();
		// use the list of batchId Strings to make a list of Batches
		for (UserBatch ub : ubatches) {
			batches.add(batchService.getSingleBatch(ub.getBatchId()));
		}
		return batches;
	}

	/**
	 * this method is supposed to add a new association between a user and a batch.
	 * This way Admins can adjust the batches viewable by a particular user
	 * 
	 * @param userId
	 * @param batchId
	 * @return the entire batch repository with a new saved association
	 */
	public UserBatch addPair(int userId, String batchId) {
		// Check User and batch
		if (userService.check(userId) == false & batchService.check(batchId) == false) {
			return null;
		}
		UserBatch ub = userBatchRepository.save(new UserBatch(userId, batchId));
		return ub;
	}

	/**
	 * this returns all the batches. Useful for if you are an admin and thus need to
	 * see all batches when accessing
	 * 
	 * @return an arraylist with all the batches contained inside
	 */
	public List<UserBatch> findAll() {
		List<UserBatch> ubatches = new ArrayList<>();
		try {
			ubatches = userBatchRepository.findAll();
		} catch (Exception e) {
			System.out.println(e);
		}
		return ubatches;
	}

	/**
	 * Retrieves from Caliber the ongoing batches that fit the user's current
	 * preferences and uses them to generate new UserBatch records after deleting
	 * the user's old UserBatch records.
	 * 
	 * @param userId The user whose UserBatches are being refreshed
	 * @return List<UserBatch> The user's new list of UserBatches
	 */
	public List<UserBatch> refreshUserBatchesForUser(int userId) {
		UserProfile preferences = userService.getProfileById(userId);
		List<Batch> preferredBatches = batchService.getBatchesByPreferences(preferences);

		List<UserBatch> newUBs = new ArrayList<UserBatch>();
		for (Batch tempBatch : preferredBatches) {
			newUBs.add(new UserBatch(userId, tempBatch.getBatchId()));
		}

		userBatchRepository.deleteByUserId(userId); // Removes all of the user's old UserBatches
		userBatchRepository.saveAll(newUBs); // Saves the user's new userbatches according to their current preferences

		return newUBs;
	}
}
