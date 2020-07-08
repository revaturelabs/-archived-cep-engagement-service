package com.cepengagementservice.UserBatchTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


import com.cepengagementservice.Models.UserBatch;

import org.junit.jupiter.api.Test;

public class UserBatchTest {

    @Test
    void createUserBatch(){
        UserBatch test = new UserBatch(1, "last");
        assertNotNull(test, "The constructor UserBatch must create an object");

    }

}