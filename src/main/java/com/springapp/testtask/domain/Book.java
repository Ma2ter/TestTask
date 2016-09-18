package com.springapp.testtask.domain;


import java.io.Serializable;


public class Book implements Serializable {

	private int id;
	private String name;
	private String author;
	private String description;
	private String imageURI;
	
	public Book(){
		
	}
	
	public Book(String name, String author, String description, String imageURI) {
		this.name = name;
		this.author = author;
		this.description = description;
		this.imageURI = imageURI;
	}
	
	public Book(int id, String name, String author, String description, String imageURI) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.description = description;
		this.imageURI = imageURI;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageURI() {
		return imageURI;
	}
	public void setImageURI(String imageURI) {
		this.imageURI = imageURI;
	}
	
	
	
}
