package com.cepengagementservice.UserTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cepengagementservice.Models.User;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void createUser(){
        User test = new User(1,"first", "last","p","pass", "comp","role", "888");
        assertNotNull(test, "The constructor must create an object");
    

    }

}