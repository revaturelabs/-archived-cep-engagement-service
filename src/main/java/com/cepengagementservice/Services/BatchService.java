package com.cepengagementservice.Services;

import com.cepengagementservice.Models.Batch;

import org.springframework.stereotype.Service;

@Service
public class BatchService {

    public Batch getSingleBatch(String id) {
        Batch b = Batch.getBatchById(id);

        return b;

    }

}