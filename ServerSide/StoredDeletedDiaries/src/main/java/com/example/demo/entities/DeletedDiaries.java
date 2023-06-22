package com.example.demo.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Deleted_Diaries")
public class DeletedDiaries {

	@Id
	private int ID;
	private String Username;
	private String Header;
	private Date dateDeleted;
	private Date LastEdited;
	private String Content;
	public int getID() {
		return ID;
	}
	public Date getLastEdited() {
		return LastEdited;
	}
	public void setLastEdited(Date lastEdited) {
		LastEdited = lastEdited;
	}
	public void setID(int iD) {
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
	public DeletedDiaries(int iD, String username, String header, Date dateDeleted, Date lastEdited, String content) {
		super();
		ID = iD;
		Username = username;
		Header = header;
		this.dateDeleted = dateDeleted;
		LastEdited = lastEdited;
		Content = content;
	}
	public DeletedDiaries() 
	{
		
	}
}
