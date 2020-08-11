package com.register.model;

/**
 * This class is what's used when sending a pending user to the cep-engagement service.
 * @author Syste
 *
 */
public class PendingUserSend {


	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String company;
	
	private String role;
	
	private String phone;
	
	private boolean resetPassword = true;
	
	private String profileDeadline;
	
	private Integer profileCount;

	//All args
	public PendingUserSend(String firstName, String lastName, String email, String password, String company,
			String role, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.company = company;
		this.role = role;
		this.phone = phone;
		this.resetPassword = true;
		this.profileDeadline = null;
		this.profileCount = null;
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
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public boolean isResetPassword() {
		return resetPassword;
	}

	public void setResetPassword(boolean resetPassword) {
		this.resetPassword = resetPassword;
	}

	public String getProfileDeadline() {
		return profileDeadline;
	}

	public void setProfileDeadline(String profileDeadline) {
		this.profileDeadline = profileDeadline;
	}

	public Integer getProfileCount() {
		return profileCount;
	}

	public void setProfileCount(Integer profileCount) {
		this.profileCount = profileCount;
	}

	@Override
	public String toString() {
		return "PendingUserSend [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", company=" + company + ", role=" + role + ", phone=" + phone + ", resetPassword="
				+ resetPassword + ", profileDeadline=" + profileDeadline + ", profileCount=" + profileCount + "]";
	}

	
	
}
