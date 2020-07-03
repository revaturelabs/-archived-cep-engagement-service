package com.cepengagementservice.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity

public class UserBatch {

    /**
     *
     */
    @Id
    @GeneratedValue
    int id;
    int userId;
    String batchId;

    public UserBatch(int userId, String batchId) {
        this.userId = userId;
        this.batchId = batchId;
    }

}