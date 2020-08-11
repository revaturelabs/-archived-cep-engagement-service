package com.cepengagementservice.Repositories;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cepengagementservice.Models.UserBatch;

/**

 * Interface that extends JpaReository
 * For SQL queries 
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
    
    /**
     * Deletes every UserBatch for a given user
     * @param userId
     */
    @Transactional
    @Modifying
    public void deleteByUserId(int userId);

}