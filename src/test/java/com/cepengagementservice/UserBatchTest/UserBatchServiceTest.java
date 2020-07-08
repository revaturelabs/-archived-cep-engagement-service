package com.cepengagementservice.UserBatchTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.cepengagementservice.Models.Batch;
import com.cepengagementservice.Models.UserBatch;
import com.cepengagementservice.Models.dto.BatchDTO;
import com.cepengagementservice.Repositories.UserBatchRepository;
import com.cepengagementservice.Services.BatchService;
import com.cepengagementservice.Services.UserBatchService;
import com.cepengagementservice.Services.UserServices;
import com.sun.tools.javac.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UserBatchServiceTest {

    @Mock
    UserBatchRepository UBRepository = mock(UserBatchRepository.class);

    @Mock
    BatchService BService = mock(BatchService.class);

    @Mock
    UserServices UService = mock(UserServices.class);

    @InjectMocks
    private UserBatchService UBService;
    @Test
    public void getSingleBatchByIdTest()
    {
        BatchDTO expected = new BatchDTO();
        expected.setBatchId("TR-1000");
        expected.setName("Mock Batch 1");
        expected.setStartDate("2016-01-01");
        expected.setEndDate("2016-03-11");
        expected.setSkill("Big Data");
        expected.setLocation("West Virginia");
        expected.setType("Revature");
        expected.setGoodGrade(70);
        expected.setPassingGrade(80);
        expected.setCurrentWeek(11);

        ArrayList<UserBatch> batches= new ArrayList<UserBatch>();
        ArrayList<BatchDTO> bDTOList = new ArrayList<BatchDTO>();
        UserBatch user = new UserBatch(1, "TR-1000");
        batches.add(user);
        bDTOList.add(expected);
        when(UBRepository.findByUserId(1)).thenReturn(batches);
        when(BService.getSingleBatchDTO("TR-1000")).thenReturn(expected);
       assertEquals(bDTOList, UBService.getBatchesDTOByUserId(1), "BatchesDTO services must return API Data Correctly.");
      }

      @Test
      public void addPairTest(){
        UserBatch user = new UserBatch(1, "TR-1000");
        when(UService.check(1)).thenReturn(true);
        when(BService.check("TR-1000")).thenReturn(true);
        when(UBRepository.save(user)).thenReturn(user);

        assertEquals(user,UBService.addPair(1,"TR-1000"), "User batch must be saved if previously stored in DB.");
      }

      @Test
      public void addPairNoUserTest(){
        UserBatch user = new UserBatch(1, "TR-1000");
        when(UService.check(1)).thenReturn(false);
        when(BService.check("TR-1000")).thenReturn(true);
      //  when(UBRepository.save(user)).thenReturn(user);

        assertEquals(null,UBService.addPair(1,"TR-1000"), "User batch must not save if user not previously stored in DB.");
      }


      @Test
      public void addPairNoBatchTest(){
        UserBatch user = new UserBatch(1, "TR-1000");
        when(UService.check(1)).thenReturn(true);
        when(BService.check("TR-1000")).thenReturn(false);
      //  when(UBRepository.save(user)).thenReturn(user);

        assertEquals(null,UBService.addPair(1,"TR-1000"), "User batch must not save if batch not previously stored in DB.");
      }



      @Test
      public void findAllTest(){
        UserBatch user = new UserBatch(1, "TR-1000");
        ArrayList<UserBatch> batches= new ArrayList<UserBatch>();
        batches.add(user);
     
        when(UBRepository.findAll()).thenReturn(batches);

        assertEquals(batches,UBService.findAll(), "User batch must not save if user not previously stored in DB.");
      }
}