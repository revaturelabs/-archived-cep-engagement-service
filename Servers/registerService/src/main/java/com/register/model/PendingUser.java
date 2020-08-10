package com.register.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class represents a user waiting to be approved in the database
 * @author Unknown
 *
 */
@Entity
@Table(name="PendingUser")
public class PendingUser {

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@Column(name="first_name", nullable=false)
	private String firstName;
	
	@Column(name="last_name", nullable=false)
	private String lastName;
	
	@Column(name="email", nullable=false, unique=true)
	private String email;
	
	@Column(name="company", nullable=false)
	private String company;
	
	@Column(name="role", nullable=false)
	private String role = "ROLE_CLIENT";
	
	@Column(name="phone", nullable=false)
	private String phone;
	
	//No args
	public PendingUser(){
		
	}

	//All args
	public PendingUser(int userId, String firstName, String lastName, String email, String company,
			String role, String phone) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email.toLowerCase();
		this.company = company;
		this.role = role;
		this.phone = phone;
	}

	//All args w/o userId
	public PendingUser(String firstName, String lastName, String email, String company, String role,
			String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.company = company;
		this.role = role;
		this.phone = phone;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "PendingUser [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", company=" + company + ", role=" + role + ", phone=" + phone
				 + "]";
	}
	
}
