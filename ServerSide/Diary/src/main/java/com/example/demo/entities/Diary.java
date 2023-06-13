package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "Diary")
public class Diary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	private String Header;
	private Date DateWrittenOrUpdated;
	private String Content;
	private Boolean Markup;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
    @JoinColumn(name = "Username")
	private Writer writer;
	
	

	public Writer getWriter() {
		return writer;
	}



	public void setWriter(Writer writer) {
		this.writer = writer;
	}



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







	public Diary(Long iD, String header, Date dateWrittenOrUpdated, String content, Boolean markup, Writer writer) {
		super();
		ID = iD;
		Header = header;
		DateWrittenOrUpdated = dateWrittenOrUpdated;
		Content = content;
		Markup = markup;
		this.writer = writer;
	}



	public Diary()
	{
		
	}
	
}
