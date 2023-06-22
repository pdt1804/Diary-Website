package com.example.demo.entities;

import java.util.Date;

public class DeletedDiary {
	private Long ID;
	private String Username;
	private String Header;
	private Date dateDeleted;
	private Date LastEdited;
	private String Content;
	public Long getID() {
		return ID;
	}
	public Date getLastEdited() {
		return LastEdited;
	}
	public void setLastEdited(Date lastEdited) {
		LastEdited = lastEdited;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getHeader() {
		return Header;
	}
	public void setHeader(String header) {
		Header = header;
	}
	public Date getDateDeleted() {
		return dateDeleted;
	}
	public void setDateDeleted(Date dateDeleted) {
		this.dateDeleted = dateDeleted;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public DeletedDiary(Long long1, String username, String header, Date dateDeleted, Date lastEdited, String content) {
		super();
		ID = long1;
		Username = username;
		Header = header;
		this.dateDeleted = dateDeleted;
		LastEdited = lastEdited;
		Content = content;
	}
	public DeletedDiary() 
	{
		
	}
}
