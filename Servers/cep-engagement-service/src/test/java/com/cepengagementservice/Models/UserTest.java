package com.cepengagementservice.Models;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

public class UserTest {
	
	@Test
	void test() {
		User user = new User();
		user.setUserId(1);
		user.setFirstName("Michael");
		user.setLastName("Worrell");
		user.setEmail("banobo@gmail.com");
		user.setPassword("password");
		user.setCompany("Revature");
		user.setRole("ROLE_ADMIN");
		user.setPhone("(123)456-7890");
		
		assertEquals(user, user);
		assertEquals(1, user.getUserId());
		assertEquals("Michael", user.getFirstName());
		assertEquals("Worrell", user.getLastName());
		assertEquals("banobo@gmail.com", user.getEmail());
		assertEquals("password", user.getPassword());
		assertEquals("Revature", user.getCompany());
		assertEquals("ROLE_ADMIN", user.getRole());
		assertEquals("(123)456-7890", user.getPhone());
	}
	
	@Test
	void UserTest() {
		User user1 = new User("Michael", "Worrell", "banobo@gmail.com", "password", "revature", "ROLE_ADMIN", "(123)456-7890");
		
		assertEquals("Michael", user1.getFirstName());
		assertEquals("Worrell", user1.getLastName());
		assertEquals("banobo@gmail.com", user1.getEmail());
		assertEquals("password", user1.getPassword());
		assertEquals("revature", user1.getCompany());
		assertEquals("ROLE_ADMIN", user1.getRole());
		
		assertEquals (user1, user1);
	}
	
	@Test
	void UserTestMethods() {
		User user1 = new User("Michael", "Worrell", "banobo@gmail.com", "password", "revature", "ROLE_ADMIN", "(123)456-7890");
		
		User user2 = new User("Michael", "Worrell", "banobo@gmail.com", "password", "revature", "ROLE_ADMIN", "(123)456-7890");
	
		boolean bool = user1.equals(user2);
		
		assertEquals(true, bool);
		
	}
	
	/*
	 * @Test public void simpleEqualsContract() {
	 * EqualsVerifier.simple().forClass(User.class).verify(); }
	 */

}
