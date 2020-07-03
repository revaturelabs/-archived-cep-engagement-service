package com.cepengagementservice.Services;

import com.cepengagementservice.Models.Batch;
import com.cepengagementservice.Models.dto.BatchDTO;

import org.springframework.stereotype.Service;

@Service
public class BatchService {

    public Batch getSingleBatch(String id) {
        Batch b = Batch.getBatchById(id);

        return b;

    }

    public BatchDTO getSingleBatchDTO(String id) {
        BatchDTO b = Batch.getBatchDTOById(id);

        return b;

    }

}