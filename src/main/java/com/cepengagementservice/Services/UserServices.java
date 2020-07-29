package com.cepengagementservice.Services;

import java.util.ArrayList;
import java.util.List;

import com.cepengagementservice.Models.User;
import com.cepengagementservice.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
/**
 * 
 * @author Unknown
 * this is supposed to utilize the userRepository in order to access and store
 * all the clients, admins, and other potential users
 *
 */
@Service @Primary

public class UserServices {

    @Autowired
    private UserRepository userRepository;
    /**
     * this is to get all registered users
     * @return an arraylist with all users contained within
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    /**
     * this is a function used to primarily store a new user.  Has a if function
     * within to check whether or not the user already exists, and saves only 
     * if the user has not been previously registered by the email address
     * @param user is a user object
     * @return a boolean to indicate whether the save was successful
     */
    // Better way instead of true false?
    public Boolean addUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    /**
     * Check to see if the user exists
     * (Note from Miki: this is redundant.  The previous function is the same except
     * it integrates the functionality of save which is an evolution from this, and so
     * I would suggest removing it)
     * 
     * @param userId
     * @return boolean
     */
    public boolean check(int userId) {
        if (this.getUserById(userId) != null)
            return true; // there is a user
        else
            return false;
    }

    /**
     * basic method to find by email
     * @param email
     * @return a user
     */
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    /**
     * this is a method to find the user by their ID number
     * @param id
     * @return a user
     */
    // Either do Optional<Users> or check yourself.
    public User getUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    // public List<User> getByBatchId(String batchId){
    // return userRepository.findByBatchId(batchId);
    // }
    /**
     * this is a method to update the user
     * @param user
     * @return the user repository with the new user having been saved(updated)
     */
    public User updateUser(User user) {
        return userRepository.save(user);
    }

}