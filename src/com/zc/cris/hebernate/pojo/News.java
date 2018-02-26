package com.zc.cris.hebernate.pojo;

import java.sql.Date;

public class News {

	private Integer id;
	private String title;
	private String author;
	private Date date;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public News(Integer id, String title, String author, Date date) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.date = date;
	}
	
	public News(String title, String author, Date date) {
		super();
		this.title = title;
		this.author = author;
		this.date = date;
	}
	public News() {
		super();
		
	}
	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", author=" + author + ", date=" + date + "]";
	}
}	
