package com.register.model;

//Used to receive JSON body from register path
public class RegisterInfo {

	private String email;
	
	private String password;
	
	public RegisterInfo() {
		
	}

	public RegisterInfo(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "RegisterInfo [email=" + email + ", password=" + password + "]";
	}
	
	
	
}
