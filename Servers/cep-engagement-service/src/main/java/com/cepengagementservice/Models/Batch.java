package com.cepengagementservice.Models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cepengagementservice.Models.dto.BatchDTO;

import org.springframework.web.client.RestTemplate;

import lombok.Data;

@Data
/**
 * This class represents the batch object.
 * The data for the batches will be generated from the caliber service.
 * @Author le7204
 */
public class Batch {

    String batchId;
    String name;
    String startDate;
    String endDate;
    String skill;
    String location;
    String type;
    int goodGrade;
    int passingGrade;
    int currentWeek;
    List<?> employeeAssignments;
    List<?> associateAssignments;

    /**
     * This method will get the batches by id from caliber (this may not be used)
     * @param id is the batch id
     * @return the specific batch
     */
    // TODO: abstract URIs
    public static Batch getBatchById(String id) {
        final String uri = "http://34.82.182.44:80/mock/training/batch/{id}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);

        RestTemplate restTemplate = new RestTemplate();
        Batch result = restTemplate.getForObject(uri, Batch.class, params);

        return result;
    }

    /**
     * This method will get the batches by id from caliber
     * @param id is the batch id
     * @return the specific batch by it's id
     */
    public static BatchDTO getBatchDTOById(String id) {
        final String uri = "http://34.82.182.44:80/mock/training/batch/{id}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);

        RestTemplate restTemplate = new RestTemplate();
        BatchDTO result = restTemplate.getForObject(uri, BatchDTO.class, params);

        return result;
    }
    
    /**
     * Returns a list of ongoing batches from the Caliber API
     * @return List<Batch>
     */
    public static List<Batch> getCurrentBatches(){
    	final String uri = "http://34.82.182.44:80/mock/training/batch/current";
    	
    	RestTemplate restTemplate = new RestTemplate();
    	Batch[] result = restTemplate.getForObject(uri, Batch[].class);
    	
    	return Arrays.asList(result);
    }

    /**
     * Returns a list of ongoing batches from the Caliber API
     * @return List<Batch>
     */
    public static List<BatchDTO> getCurrentBatchDTOs(){
    	final String uri = "http://34.82.182.44:80/mock/training/batch/current";
    	
    	RestTemplate restTemplate = new RestTemplate();
    	BatchDTO[] result = restTemplate.getForObject(uri, BatchDTO[].class);
    	
    	return Arrays.asList(result);
    }
}
