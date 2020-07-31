package com.cepengagementservice.security;

//Represents the structure of a response containing a JWT @author Nicholas Larkin

import java.io.Serializable;

public class JwtTokenResponse implements Serializable {

	private static final long serialVersionUID = 1651650916454508L;

	private final String token;

	// stores the token
	public JwtTokenResponse(String token) {
		this.token = token;
	}

	// returns the stored token
	public String getToken() {
		return this.token;
	}
}