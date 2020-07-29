package com.cepengagementservice.Repositories;

import java.util.List;

import com.cepengagementservice.Models.UserBatch;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This interface deals with UserBatch related functionality
 * @author Unknown
 *
 */
@Repository
public interface UserBatchRepository extends JpaRepository<UserBatch, Integer> {

    /**
     * 
     * @param userId
     * @return List of user_batches related to the userId
     */
    @Query("select u from #{#entityName} u where u.userId = ?1")
    public List<UserBatch> findByUserId(int userId);
    public List<UserBatch> findAll();

}