package com.cepengagementservice.UserBatchTest;

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
import com.cepengagementservice.Controllers.UserBatchController;
import com.cepengagementservice.Models.Batch;
import com.cepengagementservice.Models.Request;
import com.cepengagementservice.Models.User;
import com.cepengagementservice.Models.UserBatch;
import com.cepengagementservice.Models.dto.BatchDTO;
import com.cepengagementservice.Services.RequestService;
import com.cepengagementservice.Services.UserBatchService;
import com.cepengagementservice.Services.UserServices;



@ExtendWith(SpringExtension.class)
public class UserBatchControllerTest {

    @Mock
    private UserBatchService userBatchService = mock(UserBatchService.class);
    
    @InjectMocks
    private UserBatchController userBatchController;


    @Test
    public void testGetAllUB(){
    	UserBatch UB1 = new UserBatch();
    	UserBatch UB2 = new UserBatch();
    	List<UserBatch> batches = new ArrayList<UserBatch>();
    	batches.add(UB1);
    	batches.add(UB2);
    	when(userBatchService.findAll()).thenReturn(batches);
    	assertEquals(new ResponseEntity<List<UserBatch>>(batches,  HttpStatus.OK), userBatchController.getAllUB(), "List of all batches in database should be returned.");
    }
    @Test
    public void testGetAllMyBatches(){
        User user = new User(1,"first", "last","p","pass", "comp","role", "888", true, new ArrayList<Request>());
    	Batch B1 = new Batch();
    	Batch B2 = new Batch();
    	List<Batch> batches = new ArrayList<Batch>();
    	batches.add(B1);
    	batches.add(B2);
    	when(userBatchService.getBatchesByUserId(1)).thenReturn(batches);
    	assertEquals(new ResponseEntity<List<Batch>>(batches,  HttpStatus.OK), userBatchController.getAllMyBatches(user.getUserId()), "List of all batches tied to user.");
    }
    @Test
    public void testGetAllMyBatchesDTO(){
        User user = new User(1,"first", "last","p","pass", "comp","role", "888", true, new ArrayList<Request>());
    	BatchDTO BD1 = new BatchDTO();
    	BatchDTO BD2 = new BatchDTO();
    	List<BatchDTO> batches = new ArrayList<BatchDTO>();
    	batches.add(BD1);
    	batches.add(BD2);
    	when(userBatchService.getBatchesDTOByUserId(1)).thenReturn(batches);
    	assertEquals(new ResponseEntity<List<BatchDTO>>(batches,  HttpStatus.OK), userBatchController.getAllMyBatchesDTO(user.getUserId()), "List of all batches' DTO tied to user.");
    }

 
}