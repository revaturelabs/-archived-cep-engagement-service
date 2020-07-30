package com.cepengagementservice.security;

//Represents the structure of request to get a JWT Token. @author Nicholas Larkin

import java.io.Serializable;

public class JwtTokenRequest implements Serializable {

	private static final long serialVersionUID = -56716819845615L;

	private String email;
	private String password;

	public JwtTokenRequest() {
		super();
	}

	// takes email and password from request body and sets them on JWT
	public JwtTokenRequest(String email, String password) { 
		this.setEmail(email);
		this.setPassword(password);
	}

	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}