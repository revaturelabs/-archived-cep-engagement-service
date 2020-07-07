package com.cepengagementservice;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cepengagementservice.Controllers.AssociateGraphController;
import com.cepengagementservice.Models.AssociateGraph;

import org.junit.Assert;



//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AssociateGraphTest {


	@LocalServerPort
	int randomServerPort;

	@Autowired
	AssociateGraphController AGC = new AssociateGraphController();
	
	
	@Test
	public void testAssosicateController() throws URISyntaxException
	{
			
		List<AssociateGraph> k = (List<AssociateGraph>) AGC.getAssociateGrade("mock1.associate28f51b0f-58ea-428f-9446-63f7cb737554@mock.com", "TR-1000");
		  
		    Assert.assertEquals(k.get(0),( (List<AssociateGraph>)AGC.getAssociateGrade("mock1.associate28f51b0f-58ea-428f-9446-63f7cb737554@mock.com", "TR-1000")).get(0));
		
	}

}



