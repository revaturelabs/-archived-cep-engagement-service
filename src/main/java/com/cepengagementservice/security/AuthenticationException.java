package com.cepengagementservice.security;

//Use to throw invalid credentials or token exception @author Nicholas Larkin

/**
 * @author Unknown
 * This will throw an RuntimeException when an invalid credentials or token appears
 */
public class AuthenticationException extends RuntimeException {   
	
private static final long serialVersionUID = 813478829857683389L; //generated

public AuthenticationException(String message, Throwable cause) {
      super(message, cause);
  }
}