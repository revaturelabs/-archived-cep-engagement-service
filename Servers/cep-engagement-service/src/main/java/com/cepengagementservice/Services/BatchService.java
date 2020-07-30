package com.cepengagementservice.Services;

import com.cepengagementservice.Models.Batch;
import com.cepengagementservice.Models.dto.BatchDTO;

import org.springframework.stereotype.Service;
/**
 * 
 * @author unknown
 * This is the service layer for Batches.  You utilize this in order to
 * pull batches by ID and to check if batches exist
 *
 */
@Service
public class BatchService {

    /**
     * 
     * this is the method to fetch a single batch
     * @param String id
     * @return Batch
     */
    public Batch getSingleBatch(String id) {
        return Batch.getBatchById(id);
    }

    /**
     * 
     * this is the method to get a single batch from the DTO
     * @param String id
     * @return Batch
     */
    public BatchDTO getSingleBatchDTO(String id) {
        return Batch.getBatchDTOById(id);

    }

    /**
     * 
     * this is a check method used to figure out if the batch actually exists for the ID input.  Checks the batch not the DTO
     * @param batchId
     * @return boolean
     */
	public boolean check(String batchId) {
		if (getSingleBatch(batchId)!=null)
			return true;
		else
			return false;
	}

}