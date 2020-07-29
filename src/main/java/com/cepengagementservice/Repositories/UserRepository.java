package com.cepengagementservice.Repositories;

// import java.util.List;

import com.cepengagementservice.Models.User;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interface extends JpaRepositry
 * For SQL queries 
 * @author Unknown
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Assuming email is unique
    User findByEmail(String email);

    // @Query(value = "SELECT u FROM User u INNER JOIN u.batches ub WHERE ub.batchId
    // = :batchId")
    // List<User> findByBatchId(@Param("batchId") String batchId);

}