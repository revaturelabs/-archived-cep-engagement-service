package com.cepengagementservice.RequestTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
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
import com.cepengagementservice.Controllers.RequestController;
import com.cepengagementservice.Controllers.UserBatchController;
import com.cepengagementservice.Models.Batch;
import com.cepengagementservice.Models.Request;
import com.cepengagementservice.Models.User;
import com.cepengagementservice.Models.UserBatch;
import com.cepengagementservice.Models.Request.RequestType;
import com.cepengagementservice.Models.Request.Status;
import com.cepengagementservice.Models.dto.BatchDTO;
import com.cepengagementservice.Services.BatchService;
import com.cepengagementservice.Services.RequestService;
import com.cepengagementservice.Services.SNSPublisherService;
import com.cepengagementservice.Services.UserBatchService;
import com.cepengagementservice.Services.UserServices;



@ExtendWith(SpringExtension.class)
public class RequestControllerTest {
	@Mock
	private RequestService requestService = mock(RequestService.class);
	@Mock
	private SNSPublisherService snsPublisherService = mock(SNSPublisherService.class);
    @InjectMocks
    private RequestController requestController;


    @Test
    public void testGetFullBatch(){
    	List<Request> rList = new ArrayList<Request>();
    	when(requestService.findAll()).thenReturn(rList);
    	assertEquals(rList, requestController.getAllInterventions(), "Returns the full batch information.");
    }
    @Test
    public void testAddIntervention(){
        Date startTime = new Date();
        Date endTime = new Date();
        Request request = new Request("1", 1, startTime, endTime, false, Status.Pending, RequestType.Talent, "Test");
    	assertEquals("Intervention added", requestController.addIntervention(request), "Returns message saying intervention was added.");
    }
 
}