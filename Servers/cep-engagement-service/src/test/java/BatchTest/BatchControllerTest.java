package BatchTest;

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
public class BatchControllerTest {
	@Mock
    private BatchService batchService = mock(BatchService.class);
    
    
    @InjectMocks
    private BatchController batchController;


    @Test
    public void testGetFullBatch(){
    	Batch B1 = new Batch();
    	B1.setBatchId("1");
    	when(batchService.getSingleBatch(B1.getBatchId())).thenReturn(B1);
    	assertEquals(new ResponseEntity<Batch>(B1, HttpStatus.OK), batchController.getFullBatch(B1.getBatchId()), "Returns the full batch information.");
    }

    @Test
    public void testGetDTOBatch(){
    	Batch B1 = new Batch();
    	B1.setBatchId("1");
    	BatchDTO BD1 = new BatchDTO();
    	when(batchService.getSingleBatchDTO(B1.getBatchId())).thenReturn(BD1);
    	assertEquals(new ResponseEntity<BatchDTO>(BD1, HttpStatus.OK), batchController.getDTOBatch(B1.getBatchId()), "Returns the full batch information.");
    }
 
}