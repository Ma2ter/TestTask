package com.springapp.testtask.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springapp.testtask.domain.Book;
import com.springapp.testtask.repository.BookDAO;

@Service
public class SimpleBookManager implements BookManager {

	@Autowired
	private BookDAO bookDao;
	
	@Override
	public List<Book> getBooks() {
		
		return bookDao.getBookList();
	}

	@Override
	public void addBook(Book book) {
		bookDao.addBook(book);
		
	}

	@Override
	public void deleteBook(Book book) {
		bookDao.deleteBook(book);
		
	}
	
	public void setBookDao(BookDAO bookDao) {
        this.bookDao = bookDao;
	}

	public Book getBookById(int id){
		
		return bookDao.getBookById(id);
	}
	
	@Override
	public List<Book> getBooks(String cond, String value) {
		
		return bookDao.searchBooks(cond, value);
	}

	@Override
	public void updateBook(Book book) {
		bookDao.updateBook(book);
		
		
	}

}
