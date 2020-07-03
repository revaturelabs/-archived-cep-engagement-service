package com.cepengagementservice.Services;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 
     * @param userId
     * @return A List of Batch objects Related to the UserId
     */
    public List<Batch> getBatchesByUserId(int userId) {
        List<String> batchids = userBatchRepository.findAllBatchIdByUserID(userId);
        List<Batch> batches = new ArrayList<>();
        // use the list of batchId Strings to make a list of Batches
        for (String id : batchids) {
            batches.add(batchService.getSingleBatch(id));
        }
        return batches;
    }

    public int addPair(int userId, String batchId)
    {
        //Check User
        if (userService.check(userId) {
            return -1;
        }

        //Check Batch



        userBatchRepository.save(new UserBatch(userId,batchId));
        return true;
    }
}
