package com.cepengagementservice.Models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	void testGettersSetters() {
		User user = new User();
		
		user.setEmail("test@email.com");
		user.setCompany("company");
		user.setFirstName("firstName");
		user.setLastName("lastName");
		user.setRole("role");
		user.setPassword("password");
		user.setPhone("phone");
		user.setUserId(1);
		
		assertEquals("test@email.com", user.getEmail());
		assertEquals("company", user.getCompany());
		assertEquals("firstName", user.getFirstName());
		assertEquals("lastName", user.getLastName());
		assertEquals("role", user.getRole());
		assertEquals("password", user.getPassword());
		assertEquals("phone", user.getPhone());
		assertEquals(1, user.getUserId());
		
	}
	
	@Test
	void testConstructors() {
		User user = new User("firstName", "lastName", "email", "password", "company", "role", "phone");
		
		
		assertEquals("email", user.getEmail());
		assertEquals("company", user.getCompany());
		assertEquals("firstName", user.getFirstName());
		assertEquals("lastName", user.getLastName());
		assertEquals("role", user.getRole());
		assertEquals("password", user.getPassword());
		assertEquals("phone", user.getPhone());
		
	}

}
