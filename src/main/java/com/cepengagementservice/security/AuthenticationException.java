package com.cepengagementservice.security;

//Use to throw invalid credentials or token exception @author Nicholas Larkin


public class AuthenticationException extends RuntimeException {   /**
	 * 
	 */
	private static final long serialVersionUID = 813478829857683389L;



public AuthenticationException(String message, Throwable cause) {
      super(message, cause);
  }
}