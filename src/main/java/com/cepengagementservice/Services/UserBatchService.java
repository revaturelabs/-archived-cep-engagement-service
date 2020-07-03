package com.cepengagementservice.Services;

import java.util.ArrayList;
import java.util.List;

import com.cepengagementservice.Controllers.BatchController;
import com.cepengagementservice.Models.Batch;
import com.cepengagementservice.Models.UserBatch;
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
    @Autowired
    private BatchController batchController;

    /**
     * 
     * @param userId
     * @return A List of Batch objects Related to the UserId
     */
    public List<Batch> getBatchesByUserId(int userId) {
        List<String> batchids = userBatchRepository.findAllBatchIdByUserId(userId);
        List<Batch> batches = new ArrayList<>();
        // use the list of batchId Strings to make a list of Batches
        for (String id : batchids) {
            batches.add(batchService.getSingleBatch(id));
        }
        return batches;
    }

    public UserBatch addPair(int userId, String batchId) {
        // Check User and batch
        if (userService.check(userId)==false & batchController.getDTOBatch(batchId)==null) {
            return null;
        }
        UserBatch ub = userBatchRepository.save(new UserBatch(userId, batchId));
        return ub;
    }
}
