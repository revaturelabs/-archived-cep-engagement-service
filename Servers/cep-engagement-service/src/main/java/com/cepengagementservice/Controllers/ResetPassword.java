package com.cepengagementservice.Controllers;

public class ResetPassword {

	private String oldPassword;
	private String newPassword;
	private String confirmedPassword;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmedPassword() {
		return confirmedPassword;
	}

	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}

	public ResetPassword(String oldPassword, String newPassword, String confirmedPassword, String email) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmedPassword = confirmedPassword;
		this.email = email;
	}
}
