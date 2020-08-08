package com.cepengagementservice.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

class RequestTest {

	//Already written tests by previous batch
	@Test
	void test() {
		Request request = new Request();
		request.setBatchId("TR-100");
		//request.setUserId(2);
		request.setIsAllDay(true);
		
		assertEquals("TR-100", request.getBatchId(),"batchId: TR-100");
		//assertEquals(2, request.getUserId(),"userId: 2");
		assertEquals(true, request.getIsAllDay(),"isAllDay: true");
		
		System.out.println("batchId: " + request.getBatchId());
		//System.out.println("userId: " + request.getUserId());
		System.out.println("isAllDay " + request.getIsAllDay());
	}
	
	@Test
	void testGettersSetters() {
		Request req = new Request();
		
		//Description getter and setter test
		req.setDescription("test");
		assertEquals("test", req.getDescription(), "description: test");
		
		//UserId getter and setter test
		req.setUserId(1);
		assertEquals(1, req.getUserId(), "userId: 1");
		
		//RequestId getter and setter test
		req.setRequestId(1);
		assertEquals(1, req.getRequestId(), "requestId: 1");
		
		//StartTime getter and setter test
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		req.setStartTime(date);
		assertEquals(date, req.getStartTime());
		
		//EndTime getter and setter test
		req.setEndTime(date);
		assertEquals(date, req.getEndTime());
		
		
	}

}

