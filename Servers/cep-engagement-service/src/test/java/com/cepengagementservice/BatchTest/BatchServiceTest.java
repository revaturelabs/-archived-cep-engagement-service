package com.cepengagementservice.BatchTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.cepengagementservice.Controllers.UsersControllers;
import com.cepengagementservice.Controllers.BatchController;
import com.cepengagementservice.Controllers.UserBatchController;
import com.cepengagementservice.Models.Batch;
import com.cepengagementservice.Models.Request;
import com.cepengagementservice.Models.User;
import com.cepengagementservice.Models.UserBatch;
import com.cepengagementservice.Models.dto.BatchDTO;
import com.cepengagementservice.Services.BatchService;
import com.cepengagementservice.Services.RequestService;
import com.cepengagementservice.Services.UserBatchService;
import com.cepengagementservice.Services.UserServices;



@ExtendWith(SpringExtension.class)
public class BatchServiceTest {
	
	@InjectMocks
    private BatchService batchService;
    
    @Test
    public void testGetSingleBatch(){
    	Batch B1 = new Batch();
    	B1.setBatchId("TR-1000");
    	assertEquals("Mock Batch 1", batchService.getSingleBatch(B1.getBatchId()).getName(), "Returns the mock batch name.");
    }
    @Test
    public void testGetSingleBatchDTO(){
    	Batch B1 = new Batch();
    	B1.setBatchId("TR-1000");
    	BatchDTO BDTO1 = new BatchDTO();
    	BDTO1.setGoodGrade(70);
    	assertEquals(BDTO1.getGoodGrade(), batchService.getSingleBatchDTO(B1.getBatchId()).getGoodGrade(), "Returns the mock batch good grade qualification.");
    }

    @Test
    public void testCheck(){
    	Batch B1 = new Batch();
    	B1.setBatchId("TR-1000");
    	assertEquals(true, batchService.check(B1.getBatchId()), "Returns true if batch is found.");
    }
 
}