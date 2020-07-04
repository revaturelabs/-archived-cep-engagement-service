package com.cepengagementservice.Services;

import com.cepengagementservice.Models.Batch;
import com.cepengagementservice.Models.dto.BatchDTO;

import org.springframework.stereotype.Service;

@Service
public class BatchService {

    /**
     * 
     * @param String batchid
     * @return Batch
     */
    public Batch getSingleBatch(String id) {
        return Batch.getBatchById(id);
    }

    /**
     * 
     * @param String batchid
     * @return Batch
     */
    public BatchDTO getSingleBatchDTO(String id) {
        return Batch.getBatchDTOById(id);

    }

	public boolean check(String batchId) {
		if (getSingleBatch(batchId)!=null)
			return true;
		else
			return false;
	}

}