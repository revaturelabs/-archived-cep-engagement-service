package com.register.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TakenEmail")
public class TakenEmail {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int emailId;
	
	@Column(name="email")
	private String email;
	
	public TakenEmail() {
		
	}

	public TakenEmail(int emailId, String email) {
		super();
		this.emailId = emailId;
		this.email = email;
	}

	public int getEmailId() {
		return emailId;
	}

	public void setEmailId(int emailId) {
		this.emailId = emailId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "TakenEmail [emailId=" + emailId + ", email=" + email + "]";
	}
	
	
}
