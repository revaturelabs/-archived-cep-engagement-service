package com.register.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.register.model.PendingUser;
import com.register.service.PendingUserServiceImpl;


@WebMvcTest(controllers = PendingUserController.class)
@ActiveProfiles("test")
public class PendingUserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PendingUserServiceImpl pendingUserService;
	
	private List<PendingUser> users;
	
	@Mock
    private RestTemplate restTemplate;
	
	@Autowired
	ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		this.users = new ArrayList<>();
		this.users.add(new PendingUser(1, "test", "test", "test", "test", "test", "test", "test"));
		this.users.add(new PendingUser(2, "test1", "test1", "test1", "test1", "test1", "test1", "test1"));
		
		
		
	}
	
	@Test
	void shouldFetchAllPendingUsers() throws Exception{
		given(pendingUserService.allPendingUsers()).willReturn(users);
		
		this.mockMvc.perform(get("/pending/all"))
		.andExpect(status().isOk())
        .andExpect(jsonPath("$.size()", is(users.size())));
	}
	
	@Test
	void testAddUser() throws Exception{
		
		PendingUser user = new PendingUser(3, "test3", "test3", "test3", "test3", "test3", "test3", "test3");
		
		HttpHeaders headers = new HttpHeaders();

		// set Content-Type and Accept headers
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		// example of custom header
		headers.set("Authorization", "pass");
		
		HttpEntity<?> request = new HttpEntity<>(headers);
		
		String[] str = {};
		
		ResponseEntity<String[]> res = new ResponseEntity<String[]>(str, HttpStatus.OK);
		
		System.out.println(0);
		
		Mockito.when(restTemplate.exchange(ArgumentMatchers.eq("http://localhost:9015/users/email/all"), ArgumentMatchers.eq(HttpMethod.GET),
				ArgumentMatchers.eq(request), ArgumentMatchers.eq(String[].class))).thenReturn(res);
		
		System.out.println(1);
		
		this.mockMvc.perform(post("/pending/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user))
				.characterEncoding("utf-8"))
				.andExpect(status().isOk());
				
	}
	

}
