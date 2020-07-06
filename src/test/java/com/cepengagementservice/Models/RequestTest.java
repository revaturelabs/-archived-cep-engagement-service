package com.cepengagementservice.Models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RequestTest {

	@Test
	void test() {
		Request request = new Request();
		request.setBatchId(5);
		request.setUserId(2);
		request.setIsAllDay(true);
		
		assertEquals(5, request.getBatchId(),"batchId: 5");
		assertEquals(2, request.getUserId(),"userId: 2");
		assertEquals(true, request.getIsAllDay(),"isAllDay: true");
		
		System.out.println("batchId: " + request.getBatchId());
		System.out.println("userId: " + request.getUserId());
		System.out.println("isAllDay " + request.getIsAllDay());
		
		
	}

}
