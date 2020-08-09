package com.cepengagementservice.UserTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.util.ArrayList;

import com.cepengagementservice.Models.Request;
import com.cepengagementservice.Models.User;
import com.cepengagementservice.Models.UserProfile;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void createUser(){

        User test = new User("first", "last","p","pass", "comp","role", "888");
        assertNotNull(test, "The constructor must create an object");
    

    }

}