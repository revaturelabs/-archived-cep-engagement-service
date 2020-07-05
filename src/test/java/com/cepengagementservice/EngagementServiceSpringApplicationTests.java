package com.cepengagementservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EngagementServiceSpringApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	int randomServerPort;

	@Test
	public void testAssosicateController() throws URISyntaxException
	{

		    final String baseUrl = "http://localhost:" + 8080 + "/graph/associate/TR-1000/mock1.associate28f51b0f-58ea-428f-9446-63f7cb737554@mock.com";
		    URI uri = new URI(baseUrl);
		 
		    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		     System.out.println(result);
		    //Verify request succeed
		    Assertions.assertEquals(200, result.getStatusCodeValue());
		    Assertions.assertEquals(true, result.getBody().contains("traineeId"));
		    Assertions.assertEquals(true, result.getBody().contains("assessmentType"));
		    Assertions.assertEquals(true, result.getBody().contains("score"));
		    Assertions.assertEquals(true, result.getBody().contains("weight"));
		    Assertions.assertEquals(true, result.getBody().contains("week"));
	}
	
	@Test
	public void testAssosicateController2() throws URISyntaxException
	{
		// try different batch id
		    final String baseUrl = "http://localhost:" + 8080 + "/graph/associate/TR-1000/asdascascasd";
		    URI uri = new URI(baseUrl);
		 
		    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		     System.out.println(result);
		    //Verify request succeed
		    Assertions.assertEquals(200, result.getStatusCodeValue());
//		    Assert.assertEquals(true, result.getBody().contains("traineeId"));
//		    Assert.assertEquals(true, result.getBody().contains("assessmentType"));
//		    Assert.assertEquals(true, result.getBody().contains("score"));
//		    Assert.assertEquals(true, result.getBody().contains("weight"));
//		    Assert.assertEquals(true, result.getBody().contains("week"));
	}
}



