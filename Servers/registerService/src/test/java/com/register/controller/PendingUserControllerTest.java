package com.register.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

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

	@BeforeEach
	void setUp() {
		this.users = new ArrayList<>();
		this.users.add(new PendingUser(1, "test", "test", "test", "test", "test", "test", "test"));
		this.users.add(new PendingUser(2, "test1", "test1", "test1", "test1", "test1", "test1", "test1"));
	}
	
	@Test
	void shouldFetchAllPendingUsers() {
		
	}

}
