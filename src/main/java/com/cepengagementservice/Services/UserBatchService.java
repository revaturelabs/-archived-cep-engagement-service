package com.cepengagementservice.Services;

import java.util.ArrayList;
import java.util.List;

import com.cepengagementservice.Models.Batch;
import com.cepengagementservice.Models.UserBatch;
import com.cepengagementservice.Models.dto.BatchDTO;
import com.cepengagementservice.Repositories.UserBatchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBatchService {

    @Autowired
    UserBatchRepository userBatchRepository;
    @Autowired
    private BatchService batchService;
    @Autowired
    private UserServices userService;

    /**
     * 
     * @param userId
     * @return A List of Batch objects Related to the UserId
     */
    public List<BatchDTO> getBatchesDTOByUserId(int userId) {
        List<UserBatch> ubatches = new ArrayList<>();
        try {
            ubatches = userBatchRepository.findByUserId(userId);
        } catch (Exception e) {
            // TODO:Log
            System.out.println(e);
        }
        List<BatchDTO> batches = new ArrayList<>();
        // use the list of batchId Strings to make a list of BatchesDTO
        for (UserBatch ub : ubatches) {
            batches.add(batchService.getSingleBatchDTO(ub.getBatchId()));
        }
        return batches;
    }

    public List<Batch> getBatchesByUserId(int userId) {
        List<UserBatch> ubatches = new ArrayList<>();
        try {
            ubatches = userBatchRepository.findByUserId(userId);
        } catch (Exception e) {
            System.out.println(e);
        }
        List<Batch> batches = new ArrayList<>();
        // use the list of batchId Strings to make a list of Batches
        for (UserBatch ub : ubatches) {
            batches.add(batchService.getSingleBatch(ub.getBatchId()));
        }
        return batches;
    }

    public UserBatch addPair(int userId, String batchId) {
        // Check User and batch
        if (userService.check(userId) == false || batchService.check(batchId) == false) {
            return null;
        }
        UserBatch ub = userBatchRepository.save(new UserBatch(userId, batchId));
        return ub;
    }

	public List<UserBatch> findAll() {
		List<UserBatch> ubatches = new ArrayList<>();
        try {
            ubatches = userBatchRepository.findAll();
        } catch (Exception e) {
            System.out.println(e);
        }
        return ubatches;
	}
}
