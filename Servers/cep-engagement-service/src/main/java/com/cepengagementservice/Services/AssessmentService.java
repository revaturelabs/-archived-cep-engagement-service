package com.cepengagementservice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cepengagementservice.Models.Assessment;
import com.cepengagementservice.Repositories.AssessmentRepository;

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

	private AssessmentRepository assessmentRepository;
	
	public AssessmentService() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public AssessmentService(AssessmentRepository assessmentRepository) {
		super();
		this.assessmentRepository = assessmentRepository;
	}

	/**
	 * Accepts a batch id and returns a list of that batch's assessments
	 * @param batchId The batch whose assessments are to be returned
	 * @return List<Assessment>
	 */
	public List<Assessment> getByBatchId(String batchId) {
		return assessmentRepository.getAssessmentsByBatch(batchId);
	}
}
