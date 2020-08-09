package com.cepengagementservice.UserTest;

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
import com.cepengagementservice.Services.RequestService;
import com.cepengagementservice.Services.UserBatchService;
import com.cepengagementservice.Services.UserServices;



@ExtendWith(SpringExtension.class)
public class UserBatchControllerTest {

    @Mock
    private UserServices userServices = mock(UserServices.class);
    @Mock
    private UserBatchService userBatchService = mock(UserBatchService.class);
    
    @Mock
    private BCrypt mockBCrypt = mock (BCrypt.class); 
    @Mock
    private BCryptPasswordEncoder BCPEncoder = mock (BCryptPasswordEncoder.class);
    @Mock
    private RequestService requestService = mock (RequestService.class);
    @InjectMocks
    private UsersControllers usersControllers;
    @InjectMocks
    private UserBatchController userBatchController;


    @Test
    public void testGetAllBatches(){
    	UserBatch UB1 = new UserBatch();
    	UserBatch UB2 = new UserBatch();
    	List<UserBatch> batches = new ArrayList<UserBatch>();
    	batches.add(UB1);
    	batches.add(UB2);
    	when(userBatchService.findAll()).thenReturn(batches);
    }
    @Test
    public void testGetAllBatches2(){
        final String uri = "http://34.82.182.44:80/mock/training/batch/{id}";

        Map<String, String> params = new HashMap<String, String>();
        Map<String, String> params2 = new HashMap<String, String>();
        params.put("id", "1");
        params2.put("id", "2");

        RestTemplate restTemplate = new RestTemplate();
        Batch b1 = restTemplate.getForObject(uri, Batch.class, params);
        Batch b2 = restTemplate.getForObject(uri, Batch.class, params2);
    }
    
 
}