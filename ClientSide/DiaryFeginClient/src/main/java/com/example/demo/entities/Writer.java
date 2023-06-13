package com.example.demo.entities;

public class Writer {

	private String Username;
	private String Password;
	private String Email;
	private String fulName;
	private Long phoneNumber;
	
	public Writer(String username, String password, String email, String fulName, Long phoneNumber) {
		super();
		Username = username;
		Password = password;
		Email = email;
		this.fulName = fulName;
		this.phoneNumber = phoneNumber;
	}
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getFulName() {
		return fulName;
	}
	public void setFulName(String fulName) {
		this.fulName = fulName;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Writer()
	{
		
	}
	
}
