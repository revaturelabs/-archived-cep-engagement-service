package com.cepengagementservice.Services;

import java.util.ArrayList;
import java.util.List;

import com.cepengagementservice.Models.User;
import com.cepengagementservice.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service @Primary

public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

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

    
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Either do Optional<Users> or check yourself.
    public User getUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    // public List<User> getByBatchId(String batchId){
    // return userRepository.findByBatchId(batchId);
    // }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

}