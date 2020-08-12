package com.cepengagementservice.Repositories;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cepengagementservice.Models.Assessment;
import com.cepengagementservice.Models.dto.AssessmentDTO;

@Repository
public class AssessmentRepository {

	@Value("${caliber.api.assessments}")
	String caliberAssessmentUri;
	
	/**
	 * Retrieves all of the assessments a batch has taken.
	 * @param batchId Batch whose assessments we are retrieving
	 * @return List<Assessment> all of the assessments a batch has taken
	 */
	public List<Assessment> getAssessmentsByBatch(String batchId) {
        final String uri = caliberAssessmentUri + "?batchId={batchId}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("batchId", batchId);

        RestTemplate restTemplate = new RestTemplate();
        Assessment[] result = restTemplate.getForObject(uri, Assessment[].class, params);

        return Arrays.asList(result);
	}
	
	/**
	 * Retrieves all of the assessments a batch has taken.
	 * @param batchId Batch whose assessments we are retrieving
	 * @return List<Assessment> all of the assessments a batch has taken
	 */
	public List<AssessmentDTO> getAssessmentDTOsByBatch(String batchId) {
        final String uri = caliberAssessmentUri + "?batchId={batchId}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("batchId", batchId);

        RestTemplate restTemplate = new RestTemplate();
        AssessmentDTO[] result = restTemplate.getForObject(uri, AssessmentDTO[].class, params);

        return Arrays.asList(result);
	}
}
