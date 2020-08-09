package com.cepengagementservice.Models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.cepengagementservice.Models.dto.AssessmentDTO;

import lombok.Data;

@Data
public class Assessment {

	int assessmentId;
	int rawScore;
	String assessmentTitle;
	String assessmentType;
	int weekNumber;
	String batchId;
	int assessmentCategory;
	String assessmentDate;
	
	/**
	 * Retrieves all of the assessments a batch has taken.
	 * @param batchId Batch whose assessments we are retrieving
	 * @return List<Assessment> all of the assessments a batch has taken
	 */
	public static List<Assessment> getAssessmentsByBatch(String batchId) {
        final String uri = "http://34.82.182.44:80/mock/evaluation/assessments?batchId={batchId}";

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
	public static List<AssessmentDTO> getAssessmentDTOsByBatch(String batchId) {
        final String uri = "http://34.82.182.44:80/mock/evaluation/assessments?batchId={batchId}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("batchId", batchId);

        RestTemplate restTemplate = new RestTemplate();
        AssessmentDTO[] result = restTemplate.getForObject(uri, AssessmentDTO[].class, params);

        return Arrays.asList(result);
	}
}
