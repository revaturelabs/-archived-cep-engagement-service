package BatchTest;

import static org.junit.Assert.assertTrue;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
	
	@Mock
    private BatchService batchService;
    
    


	@Test
	public void testGetSingleBatch(){
    	Batch B1 = new Batch();
   		B1.setBatchId("TR-1000");
   		when(batchService.getSingleBatch(B1.getBatchId())).thenReturn(B1);
    	assertEquals(B1, batchService.getSingleBatch(B1.getBatchId()), "Returns the requested batch.");
    }
	@Test
	public void testGetSingleBatchDTO(){
    	Batch B1 = new Batch();
   		B1.setBatchId("TR-1000");
   		BatchDTO BDTO1 = new BatchDTO();
   		BDTO1.setBatchId(B1.getBatchId());
   		when(batchService.getSingleBatchDTO(B1.getBatchId())).thenReturn(BDTO1);
    	assertEquals(BDTO1, batchService.getSingleBatchDTO(B1.getBatchId()), "Returns the requested batch DTO.");
    }
	@Test
	public void testCheckBatchNotFound() {
    	Batch B1 = new Batch();
   		B1.setBatchId("TR-1000");
   		assertFalse(batchService.check(B1.getBatchId()), "Returns false if batch does not exist.");
	}
}