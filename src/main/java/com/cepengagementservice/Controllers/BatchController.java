package com.cepengagementservice.Controllers;

import com.cepengagementservice.Models.Batch;
import com.cepengagementservice.Services.BatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/batch")
public class BatchController {
    @Autowired
    private BatchService batchService;

    @RequestMapping(method = RequestMethod.GET, value = "/id")
    public ResponseEntity<?> getBatch(@RequestParam String batchId) {
        Batch batch = batchService.getSingleBatch(batchId);
        if (batch != null) {
            return new ResponseEntity<Batch>(batch, HttpStatus.OK);
        }
        return new ResponseEntity<Batch>(batch, HttpStatus.NO_CONTENT);
    }

}