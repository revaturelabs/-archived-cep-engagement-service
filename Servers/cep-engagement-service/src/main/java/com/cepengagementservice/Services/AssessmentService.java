package com.cepengagementservice.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cepengagementservice.Models.Assessment;

/**
 * Contains methods for interacting with the Assessment model, which is a
 * representation of the assessment object. All assessments are initially
 * retrieved from Caliber's Assessment API.
 * 
 * @author sgruv
 *
 */
@Service
public class AssessmentService {

	/**
	 * Accepts a batch id and returns a list of that batch's assessments
	 * @param batchId The batch whose assessments are to be returned
	 * @return List<Assessment>
	 */
	public List<Assessment> getByBatchId(String batchId) {
		return Assessment.getAssessmentsByBatch(batchId);
	}
}
