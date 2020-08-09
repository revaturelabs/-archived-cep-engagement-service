package com.cepengagementservice.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cepengagementservice.Models.Assessment;
import com.cepengagementservice.Models.Batch;
import com.cepengagementservice.Models.Category;
import com.cepengagementservice.Models.UserProfile;
import com.cepengagementservice.Models.dto.BatchDTO;

/**
 * 
 * @author unknown This is the service layer for Batches. You utilize this in
 *         order to pull batches by ID and to check if batches exist
 *
 */
@Service
public class BatchService {

	@Autowired
	private AssessmentService assessmentService;

	/**
	 * 
	 * this is the method to fetch a single batch
	 * 
	 * @param String id
	 * @return Batch
	 */
	@Cacheable("GetSingleBatch")
	public Batch getSingleBatch(String id) {
		return Batch.getBatchById(id);
	}

	/**
	 * 
	 * this is the method to get a single batch from the DTO
	 * 
	 * @param String id
	 * @return Batch
	 */
	public BatchDTO getSingleBatchDTO(String id) {
		return Batch.getBatchDTOById(id);

	}

	/**
	 * 
	 * this is a check method used to figure out if the batch actually exists for
	 * the ID input. Checks the batch not the DTO
	 * 
	 * @param batchId
	 * @return boolean
	 */
	@Cacheable("GetSingleBatchDTO")
	public boolean check(String batchId) {
		if (getSingleBatch(batchId) != null)
			return true;
		else
			return false;
	}

	@Cacheable("GetCurrentBatches")
	public List<Batch> getCurrentBatches() {
		return Batch.getCurrentBatches();
	}

	/**
	 * Retrieves the ongoing batches from Caliber's Batch API, filters them by
	 * deadline, associate count, and needed category preferences, then returns the
	 * batches that pass.
	 * 
	 * @param preferences The UserProfile object being used to filter through the
	 *                    batches.
	 * @return List<Batch>
	 */
	public List<Batch> getBatchesByPreferences(UserProfile preferences) {
		List<Batch> potentialBatches = this.getCurrentBatches();

		// FILTER BY DEADLINE
		List<Batch> finishByDeadline;

		if (preferences.getBatchDeadline() != null) {
			finishByDeadline = potentialBatches; // PLACEHOLDER
		} else {
			finishByDeadline = potentialBatches;
		}
		potentialBatches = null;

		// FILTER BY ASSOCIATE COUNT
		List<Batch> enoughAssociates;

		if (preferences.getAssociateCount() != null) {
			// Goes through finishByDeadline with a lambda that compares the size of their
			// associate list to the minimum number of associates
			enoughAssociates = finishByDeadline.stream()
					.filter(b -> b.getAssociateAssignments().size() >= preferences.getAssociateCount())
					.collect(Collectors.toList());
		} else {
			enoughAssociates = finishByDeadline;
		}
		finishByDeadline = null;

		// FILTER BY NEEDED CATEGORIES
		List<Batch> validBatches;
		int minimumCategoryMatches = (int) (preferences.getNeededCategories().size() * 0.75);

		if (minimumCategoryMatches > 0) {
			validBatches = new ArrayList<Batch>();

			/// FOR EACH BATCH...
			for (Batch potentialBatch : enoughAssociates) {
				//// RETRIEVE ASSESSMENTS BY BATCH
				List<Assessment> batchAssessments = assessmentService.getByBatchId(potentialBatch.getBatchId());
				//// MAP assessmentCategoory OUT OF EACH ASSESSMENT
				List<Integer> assessmentCategoryIds = batchAssessments.stream().map(Assessment::getAssessmentCategory)
						.collect(Collectors.toList());
				List<Integer> neededCategoryIds = preferences.getNeededCategories().stream()
						.map(Category::getCategoryId).collect(Collectors.toList());

				//// COMPARE CATEGORIES FROM ASSESSMENTS TO NEEDED CATEGORIES
				int categoryMatches = 0;
				for (Integer categoryId : assessmentCategoryIds) {
					if (neededCategoryIds.contains(categoryId)) {
						categoryMatches++;
						if (categoryMatches >= minimumCategoryMatches) {
							validBatches.add(potentialBatch);
							continue;
						}
					}
				}
			}
		} else {
			validBatches = enoughAssociates;
		}
		enoughAssociates = null;

		return validBatches;
	}
	
}