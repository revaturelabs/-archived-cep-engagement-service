//package com.cepengagementservice;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import java.net.URI;
//import java.net.URISyntaxException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//public class AssociateGraphTest {
//
//	@Test
//	void contextLoads() {
//	}
//	
//	@Autowired
//	private TestRestTemplate restTemplate;
//
//	@LocalServerPort
//	int randomServerPort;
//
//	@Test
//	public void testAssosicateController() throws URISyntaxException
//	{
//
//		    final String baseUrl = "http://localhost:" + 8080 + "/graph/associate/TR-1000/mock1.associate28f51b0f-58ea-428f-9446-63f7cb737554@mock.com";
//		    URI uri = new URI(baseUrl);
//		 
//		    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
//		    //Print results for verification.
//		     System.out.println(result);
//		   
//		    Assertions.assertEquals(200, result.getStatusCodeValue());
//		    Assertions.assertEquals(true, result.getBody().contains("traineeId"));
//		    Assertions.assertEquals(true, result.getBody().contains("assessmentType"));
//		    Assertions.assertEquals(true, result.getBody().contains("score"));
//		    Assertions.assertEquals(true, result.getBody().contains("weight"));
//		    Assertions.assertEquals(true, result.getBody().contains("week"));
//	}
//
//}
//
//
//
