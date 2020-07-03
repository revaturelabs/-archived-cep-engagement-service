package com.cepengagementservice.Repositories;

import java.util.List;

import com.cepengagementservice.Models.UserBatch;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserBatchRepository extends JpaRepository<UserBatch, Integer> {

    /**
     * 
     * @param userId
     * @return List of Strings representing the batchId related to the userId
     */
    public List<String> findAllBatchIdByUserID(int userId);

}