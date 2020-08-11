package com.cepengagementservice.Repositories;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cepengagementservice.Models.Batch;
import com.cepengagementservice.Models.dto.BatchDTO;

/**
 * This class is in  charge of retrieving batch information from Caliber
 * @author Michael W
 *
 */
@Repository
public class BatchRepository {
	
	@Value("${caliber.api.batch}")
    String caliberUri;

    /**
     * This method will get the batches by id from caliber (this may not be used)
     * @param id is the batch id
     * @return the specific batch
     */
    // TODO: abstract URIs
    public Batch getBatchById(String id) {
  
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);

        RestTemplate restTemplate = new RestTemplate();
        Batch result = restTemplate.getForObject(caliberUri + "/{id}", Batch.class, params);

        return result;
    }

    /**
     * This method will get the batches by id from caliber
     * @param id is the batch id
     * @return the specific batch by it's id
     */
    public BatchDTO getBatchDTOById(String id) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);

        RestTemplate restTemplate = new RestTemplate();
        BatchDTO result = restTemplate.getForObject(caliberUri + "/{id}", BatchDTO.class, params);

        System.out.println(caliberUri+"/{id}");
        
        return result;
    }
    
    /**
     * Returns a list of ongoing batches from the Caliber API
     * @return List<Batch>
     */
    public List<Batch> getCurrentBatches(){
    	final String uri = "http://34.82.182.44:80/mock/training/batch/current";
    	
    	RestTemplate restTemplate = new RestTemplate();
    	Batch[] result = restTemplate.getForObject(caliberUri + "/current", Batch[].class);
    	
    	return Arrays.asList(result);
    }

    /**
     * Returns a list of ongoing batches from the Caliber API
     * @return List<Batch>
     */
    public List<BatchDTO> getCurrentBatchDTOs(){
    	final String uri = "http://34.82.182.44:80/mock/training/batch/current";
    	
    	RestTemplate restTemplate = new RestTemplate();
    	BatchDTO[] result = restTemplate.getForObject(uri, BatchDTO[].class);
    	
    	return Arrays.asList(result);
    }

}
