package com.example.demo.entities;

import java.util.Date;

public class Diary {

	private Long ID;
	private String Header;
	private Date DateWrittenOrUpdated;
	private String Content;
	private Boolean Markup;	
	private Writer writer;
	
	

	public Long getID() {
		return ID;
	}



	public void setID(Long iD) {
		ID = iD;
	}



	public String getHeader() {
		return Header;
	}



	public void setHeader(String header) {
		Header = header;
	}



	public Date getDateWrittenOrUpdated() {
		return DateWrittenOrUpdated;
	}



	public void setDateWrittenOrUpdated(Date dateWrittenOrUpdated) {
		DateWrittenOrUpdated = dateWrittenOrUpdated;
	}



	public String getContent() {
		return Content;
	}



	public void setContent(String content) {
		Content = content;
	}



	public Boolean getMarkup() {
		return Markup;
	}



	public void setMarkup(Boolean markup) {
		Markup = markup;
	}



	public Writer getWriter() {
		return writer;
	}



	public void setWriter(Writer writer) {
		this.writer = writer;
	}



	public Diary(String header, Date dateWrittenOrUpdated, String content, Boolean markup, Writer writer) {
		super();
		Header = header;
		DateWrittenOrUpdated = dateWrittenOrUpdated;
		Content = content;
		Markup = markup;
		this.writer = writer;
	}
	
	public Diary(String header, Date dateWrittenOrUpdated, String content, Writer writer) {
		super();
		Header = header;
		DateWrittenOrUpdated = dateWrittenOrUpdated;
		Content = content;
		this.writer = writer;
	}



	public Diary()
	{
		
	}
	
}
