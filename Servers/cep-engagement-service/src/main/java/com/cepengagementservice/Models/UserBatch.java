package com.cepengagementservice.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the junction table for the users-batch relationship
 * @author Unknown
 *
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "user_batch")
public class UserBatch {

    /**
     *
     */
    @Id
    @GeneratedValue
    @Column(name = "row_id")
    private int rowId;
    @Column(name = "user_id")
    int userId;
    @Column(name = "batch_id")
    String batchId;

    public UserBatch(int userId, String batchId) {
        this.userId = userId;
        this.batchId = batchId;
    }

    public String getBatchId() {
        return batchId;
    }
}