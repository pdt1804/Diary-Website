package com.example.demo.entities;

public class ChangePassword {

	private String currentPassword;
	private String newPassword;
	private String verifyNewPassword;
	public ChangePassword(String currentPassword, String newPassword, String verifyNewPassword) {
		super();
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
		this.verifyNewPassword = verifyNewPassword;
	}
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getVerifyNewPassword() {
		return verifyNewPassword;
	}
	public void setVerifyNewPassword(String verifyNewPassword) {
		this.verifyNewPassword = verifyNewPassword;
	}
	public ChangePassword()
	{
		
	}
}
