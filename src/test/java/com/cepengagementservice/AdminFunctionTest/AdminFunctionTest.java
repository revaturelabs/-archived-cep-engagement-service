package com.cepengagementservice.AdminFunctionTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.cepengagementservice.Controllers.UsersControllers;




@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class AdminFunctionTest {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired UsersControllers adminController;
	

	
	@Test
	public void testGetAllRequest() throws Exception{
	    final String baseUrl = "http://localhost:" + port + "/users/admin/request";
	    URI uri = new URI(baseUrl);
	 
	    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
	     
	    //Verify request succeed
	    assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	void adminContollerContextLoads() throws Exception {
		assertThat(adminController).isNotNull();
	}
}
