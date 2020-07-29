package com.cepengagementservice.security;

//Use to throw invalid credentials or token exception @author Nicholas Larkin

/**
 * This will throw an RuntimeException when an invalid credentials or token appears
 * @author Unknown
 * 
 */
public class AuthenticationException extends RuntimeException {   
	
private static final long serialVersionUID = 813478829857683389L; //generated

public AuthenticationException(String message, Throwable cause) {
      super(message, cause);
  }
}