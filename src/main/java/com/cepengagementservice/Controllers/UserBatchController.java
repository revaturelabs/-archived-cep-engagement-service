package com.cepengagementservice.Controllers;

import java.util.List;

import com.cepengagementservice.Models.Batch;
import com.cepengagementservice.Models.UserBatch;
import com.cepengagementservice.Models.dto.BatchDTO;
import com.cepengagementservice.Services.UserBatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/UB")
public class UserBatchController {
    @Autowired
    private UserBatchService userBatchService;

    /**
     * Retrieves all of the user batches
     * @return ResponseEntity with the List of UserBatches
     */
    @GetMapping(value = "/all")
    public ResponseEntity<List<UserBatch>> getAllUB() {
        try {
            List<UserBatch> batches = userBatchService.findAll();
            return new ResponseEntity<>(batches, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    /**
     * Returns a list of batches associated with the userId
     * @param int userId
     * @return ResponseEntity<List<Batch>> list of Batch objects
     */
    @GetMapping(value = "/batchesbyuser")
    public ResponseEntity<List<Batch>> getAllMyBatches(@RequestParam int userId) {
        try {
            List<Batch> batches = userBatchService.getBatchesByUserId(userId);
            return new ResponseEntity<>(batches, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Returns a list of batchesDTOs associated with the userId
     * @param int userId
     * @return ResponseEntity<List<BatchDTO>> A list of BatchDTO objects
     */
    @GetMapping(value = "/batchesbyuser/DTO")
    public ResponseEntity<List<BatchDTO>> getAllMyBatchesDTO(@RequestParam int userId) {
        try {
            List<BatchDTO> batches = userBatchService.getBatchesDTOByUserId(userId);
            return new ResponseEntity<>(batches, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    /**
     * Adds a user to a batch by their respective Ids, User can only see those batches
     * @param int userId
     * @param String batchId
     * @return ResponseEntity<UserBatch> 
     */
    @PostMapping(value = "/addPair")
    public ResponseEntity<UserBatch> makeUB(@RequestParam int userId, @RequestParam String batchId) {
        try {
            UserBatch ub = userBatchService.addPair(userId, batchId);
            return new ResponseEntity<>(ub, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}