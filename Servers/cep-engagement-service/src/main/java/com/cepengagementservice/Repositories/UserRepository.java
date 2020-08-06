package com.cepengagementservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.cepengagementservice.Models.User;

/**

 * Interface extends JpaRepositry
 * For SQL queries 
 * This interface deals with User related functionality
 * @author Unknown
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Assuming email is unique
    User findByEmail(String email);
    
    @Query("select u.email from User u")
    List<String> getAllEmail();
    
    void addUser(User user);

    // @Query(value = "SELECT u FROM User u INNER JOIN u.batches ub WHERE ub.batchId
    // = :batchId")
    // List<User> findByBatchId(@Param("batchId") String batchId);

}