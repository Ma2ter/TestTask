package com.springapp.testtask.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springapp.testtask.domain.Book;

public interface BookManager extends Serializable {

	public List<Book> getBooks();
	public List<Book> getBooks(String cond, String value);
	
	public void addBook(Book book);
	public void deleteBook(Book book);
	public void updateBook(Book book);
	
	public Book getBookById(int id);
		
}
