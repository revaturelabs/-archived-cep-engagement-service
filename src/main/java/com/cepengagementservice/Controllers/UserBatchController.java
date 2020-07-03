package com.cepengagementservice.Controllers;

import java.util.List;

import com.cepengagementservice.Models.Batch;
import com.cepengagementservice.Services.BatchService;
import com.cepengagementservice.Services.UserBatchService;
import com.cepengagementservice.Services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/UB")
public class UserBatchController {
    @Autowired
    private UserBatchService userBatchService;

    /**
     * 
     * @param userId
     * @return list of Batch objects
     */
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<?> getAllMyBatches(@RequestParam int userId) {
        try {
            List<Batch> batches = userBatchService.getBatchesByUserId(userId);
            return new ResponseEntity<List<Batch>>(batches, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<List<Batch>>(HttpStatus.NOT_FOUND);
        }

    }

}