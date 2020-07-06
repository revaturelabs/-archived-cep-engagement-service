package com.cepengagementservice.security;

//Use to throw invalid credentials or token exception @author Nicholas Larkin


public class AuthenticationException extends RuntimeException {   //does not need UID
  public AuthenticationException(String message, Throwable cause) {
      super(message, cause);
  }
}