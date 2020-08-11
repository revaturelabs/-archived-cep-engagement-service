package com.cepengagementservice.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
    
    @Query("select u.email from User u where u.role = 'ROLE_ADMIN'")
    List<String> getAdminEmail();
    
    User findByUserId(int userId);
    
    <T> T findByUserId(int userId, Class<T> type);

}