package com.register.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.register.model.DenyMessage;
import com.register.model.PendingUser;
import com.register.service.PendingUserServiceImpl;
import com.register.util.EmailSender;
import com.sun.jersey.api.client.ClientResponse.Status;

@WebMvcTest(controllers = PendingUserController.class)
@ActiveProfiles("test")
public class PendingUserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Value("${key.allemail}") // grabs value from src/main/resources/app.properties
	private String emailKey;

	@MockBean
	private PendingUserServiceImpl pendingUserService;

	private List<PendingUser> users;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private EmailSender email;

	@Autowired
	ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		this.users = new ArrayList<>();
		this.users.add(new PendingUser(1, "test", "test", "test", "test", "test", "test"));
		this.users.add(new PendingUser(2, "test1", "test1", "simonnardos@gmail.com", "test1", "test1", "test1"));

	}

	@Test
	void shouldFetchAllPendingUsers() throws Exception {
		given(pendingUserService.allPendingUsers()).willReturn(users);

		this.mockMvc.perform(get("/pending/all")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(users.size())));
	}

//	@Test
//	void testAddUser() throws Exception {
//
//		PendingUser user = new PendingUser(3, "simon", "nardos", "email@gmail.com", "company", "ROLE_CLIENT",
//				"1234567890");
//
////		ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
//
////		System.out.println(2);
//
//		// Rest Template is used to verify email is unique by querying DB in cep-service
////		RestTemplate rest = new RestTemplate(factory);
//
//		// create headers
////		HttpHeaders headers = new HttpHeaders();
//
//		// set Content-Type and Accept headers
////		headers.setContentType(MediaType.APPLICATION_JSON);
//
//		// example of custom header
////		headers.set("Authorization", emailKey);
//
//		// build the request
////		HttpEntity<?> request = new HttpEntity<>(headers);
//
//		String[] str = {};
//
//		ResponseEntity<String[]> res = new ResponseEntity<String[]>(str, HttpStatus.OK);
//
////		System.out.println("WTG");
//
//		Mockito.when(restTemplate.exchange(ArgumentMatchers.any(URI.class), ArgumentMatchers.any(HttpMethod.class),
//				ArgumentMatchers.<HttpEntity<?>>any(), ArgumentMatchers.<Class<String[]>>any())).thenReturn(res);
//
//		this.mockMvc.perform(MockMvcRequestBuilders.post("/pending/add").content(objectMapper.writeValueAsString(user))
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
//				.andExpect(status().isOk());
//	}

	@Test
	void testGeneratePassword() {
		String str = PendingUserController.generateRandomPassword(8);

		assertEquals(str.length(), 8);
	}

	@Test
	void testDenyUser() throws Exception {

//		Mockito.when(EmailSender.sendAsHtml("simonnardos@gmail.com", "Your Revature CEP account has been denied!",
//				"Sorry, you have been denied for the following reason(s): " + "hi"));

		DenyMessage mess = new DenyMessage("hi");

		PendingUser user = new PendingUser(3, "simon", "nardos", "simonnardos@gmail.com", "company", "ROLE_CLIENT",
				"1234567890");

		Mockito.when(pendingUserService.findById(1)).thenReturn(user);

		this.mockMvc.perform(MockMvcRequestBuilders.post("/pending/deny").param("id", "1")
				.content(objectMapper.writeValueAsString(mess)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")).andExpect(status().isOk());
	}

//	@Test
//	void testApproveUser() throws Exception {
//		PendingUser user = new PendingUser(3, "simon", "nardos", "simonnados@gmail.com", "company", "ROLE_CLIENT",
//				"1234567890");
//
//		Mockito.when(pendingUserService.findById(1)).thenReturn(user);
//		
////		Mockito.when(pendingUserService.deleteUser(Mockito.any(PendingUser.class)));
//		
//		
//		
//		Mockito.when(restTemplate.postForObject(Mockito.any(URI.class), Mockito.any(Object.class),
//				Mockito.any())).thenReturn("ok");		
//		
//		
//		this.mockMvc.perform(MockMvcRequestBuilders.get("/pending/approve").param("id", "1")
//				.content(objectMapper.writeValueAsString(null)).contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")).andExpect(status().isOk());
//	}

}
