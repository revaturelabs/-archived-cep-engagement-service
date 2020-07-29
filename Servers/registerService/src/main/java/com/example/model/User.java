package com.example.model;

public class User {
	private String firtName;
	private String lastName;
	
	public User() {
	}

	public User(String firtName, String lastName) {
		super();
		this.firtName = firtName;
		this.lastName = lastName;
	}

	public String getFirtName() {
		return firtName;
	}

	public void setFirtName(String firtName) {
		this.firtName = firtName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "\nUser [firtName=" + firtName + ", lastName=" + lastName + "]";
	}
	
	
}
