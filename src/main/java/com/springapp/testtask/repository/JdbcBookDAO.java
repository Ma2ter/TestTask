package com.springapp.testtask.repository;


import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.springapp.testtask.domain.Book;

import oracle.net.aso.p;

@Component	
public class JdbcBookDAO implements BookDAO {

	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Book> getBookList() {
		
		String query = "SELECT * FROM books";

		Locale.setDefault(Locale.UK);
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Book> result = new ArrayList<Book>();
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
		for(Map row : rows){
			Book book = new Book();
			book.setId(((BigDecimal)row.get("ID")).intValue());
			book.setName((String) (row.get("NAME")));
			book.setAuthor((String) (row.get("AUTHOR")));
			book.setDescription((String) (row.get("DESCRIPTION")));
			book.setImageURI((String) (row.get("IMAGEURI")));
			result.add(book);
		}
		
		return result;

	}

	@Override
	public List<Book> searchBooks(String method, String value) {

		String query = null;
		
		if(method.equalsIgnoreCase("name"))
			query = "SELECT * FROM books WHERE LOWER (name) like ?";
		if(method.equalsIgnoreCase("author"))
			query = "SELECT * FROM books WHERE LOWER (author) like ?";
		Locale.setDefault(Locale.UK);
		
		if(query == null) 
			return null;

		List <Book> result = new ArrayList<Book>();
		PreparedStatement preparedStatement = null;
		String finalValue = "%" + value + "%";
		
		try {
			preparedStatement = dataSource.getConnection().prepareStatement(query);
			preparedStatement.setString(1, finalValue.toLowerCase());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setName((String) (rs.getString("NAME")));
				book.setAuthor((String) (rs.getString("AUTHOR")));
				book.setDescription((String) (rs.getString("DESCRIPTION")));
				book.setImageURI((String) (rs.getString("IMAGEURI")));
				result.add(book);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public Book getBookById(int id){
		
		Book result = new Book();
		String query = "SELECT * FROM books WHERE id=?";
		
		try {
			PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				result.setId(rs.getInt("id"));
				result.setName((String) (rs.getString("NAME")));
				result.setAuthor((String) (rs.getString("AUTHOR")));
				result.setDescription((String) (rs.getString("DESCRIPTION")));
				result.setImageURI((String) (rs.getString("IMAGEURI")));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	@Override
	public void addBook(Book book) {
		String query = "INSERT INTO books (name, author, description, imageURI) values (?,?,?,?)";
		Locale.setDefault(Locale.UK);

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update(query, 
				new Object[]{ book.getName(), book.getAuthor(),				
				book.getDescription(), book.getImageURI()});
	}
	
	@Override
	public void deleteBook(Book book) {

		Locale.setDefault(Locale.UK);

		String query = "DELETE FROM books WHERE id=?";
		Locale.setDefault(Locale.UK);

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update(query, 
				new Object[]{book.getId()});
	}

	@Override
	public void updateBook(Book book) {
		Locale.setDefault(Locale.UK);
		String query = "UPDATE books SET name=?, author=?, description=?, imageuri=? WHERE id=?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		int rows = jdbcTemplate.update(query, 
				new Object[]{ book.getName(), book.getAuthor(),				
				book.getDescription(), book.getImageURI(), book.getId()});
		
		
		
	}

}
