package com.cepengagementservice.Services;

import java.util.List;

import com.cepengagementservice.Models.Batch;
import com.cepengagementservice.Repositories.BatchRepository;
// import com.cepengagementservice.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchServices {

    @Autowired
    private BatchRepository batchRepository;

    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }

    // TODO: get batches by user ID
    // public List<Batch> getBatchesByUser(Integer userId) {
    // return batchRepository.findByUserId(userId);
    // }

    public Batch addBatch(Batch batch) {
        return batchRepository.save(batch);
    }

    public Batch getBatchById(String batchId) {
        return batchRepository.findByBatchId(batchId);
    }
}