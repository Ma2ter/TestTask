package com.springapp.testtask.repository;


import java.util.List;

import com.springapp.testtask.domain.Book;

public interface BookDAO {

	public List<Book> getBookList();
	
	public List<Book> searchBooks(String value, String method);
	
	public void addBook (Book book);
	
	public void deleteBook(Book book);
	
	public Book getBookById(int id);
	
	public void updateBook(Book book);
	
}
