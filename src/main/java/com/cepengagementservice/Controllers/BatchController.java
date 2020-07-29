package com.cepengagementservice.Controllers;

import com.cepengagementservice.Models.Batch;
import com.cepengagementservice.Models.dto.BatchDTO;
import com.cepengagementservice.Services.BatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    /**
     * Receives a String of the batchId
     * Retrieves the batch object and sends to Client Side
     * @param batchId
     * @return Returns a ResponseEntity<?>(ResponseEntity that extends an object)
     * @apiNote Using <?> is not recommended and should be changed to <Batch> soon
     */
	@Cacheable("GetAssociateGrade")
    @RequestMapping(method = RequestMethod.GET, value = "/id")
    public ResponseEntity<?> getFullBatch(@RequestParam String batchId) {
        try {
            Batch batch = batchService.getSingleBatch(batchId);
            return new ResponseEntity<Batch>(batch, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Batch>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Accepts a string of the batchId and gets the DTO of the batch
     * @param batchId
     * @return ResponseEntity<?>(ResponseEntity that extends an object)
     * @apiNote ResponseEntity<?> is bad practices and should be changed
     */
    @RequestMapping(method = RequestMethod.GET, value = "/DTO/id")
    public ResponseEntity<?> getDTOBatch(@RequestParam String batchId) {
        try {
            BatchDTO batch = batchService.getSingleBatchDTO(batchId);
            return new ResponseEntity<BatchDTO>(batch, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<BatchDTO>(HttpStatus.NOT_FOUND);
        }

    }

}
