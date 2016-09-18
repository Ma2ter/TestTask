package com.springapp.testtask;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springapp.testtask.domain.Book;
import com.springapp.testtask.service.BookManager;

/**
 * Handles requests for the application home page.
 */

@Controller
public class BooksController {


	@Autowired
	private BookManager bookManager;
	
	//BOOK VIEWER
	@RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
	public String book(@PathVariable("id") int id, Model model) {		
		
		Book book = bookManager.getBookById(id);
		
		model.addAttribute("book", book);
		
		return "book";
	}
	
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String books(Model model) {		
		
		model.addAttribute("booksList", bookManager.getBooks() );
		
		return "books";
	}
	
	// BOOK EDITING
	@RequestMapping(value = "/books/{id}/edit", method = RequestMethod.GET)
	public String bookEdit(@PathVariable("id") int id, Model model) {		
		
		Book book = bookManager.getBookById(id);
		
		model.addAttribute("book", book);
		
		return "bookEdit";
	}
	
	@RequestMapping(value = "/books/{id}/editBook", method = RequestMethod.POST)
	public String completeBookEdit(@PathVariable("id") int id,
			@RequestParam(value="nameBook", required=true) String name,
			@RequestParam(value="nameAuthor", required=true) String author,
			@RequestParam(value="desc", required=true) String desc,
			@RequestParam(value="img", required=false) MultipartFile img,
			Model model) {		
		
		Book book = bookManager.getBookById(id);
		String imgUri;
		if(name.isEmpty() || author.isEmpty() || desc.isEmpty()){
			
			model.addAttribute("message", "All fields must be filled");
			model.addAttribute("book", book);
			
			return "bookEdit";
			
		}
		
		if(!img.isEmpty()){
			if(!img.getContentType().equals("image/jpeg")){
				
				model.addAttribute("message", "Book cover must be *.jpg file");
				model.addAttribute("book", book);
				
				return "bookEdit";
			}

			try {
				byte[] bytes = img.getBytes();
				File dir = new File("C:/webapp/resources/img");
				
				if(!dir.exists())
					dir.mkdirs();
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
				Date date = new Date();
				String fileName = dateFormat.format(date) + "." + img.getOriginalFilename().split("\\.")[1];
			
				File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
				
				BufferedOutputStream out = new BufferedOutputStream( new FileOutputStream(serverFile));
				out.write(bytes);
				out.close();
				
				File oldImageFile = new File(dir.getAbsolutePath() + File.separator + book.getImageURI());
				if(oldImageFile.exists())
					oldImageFile.delete();
				
				imgUri = fileName;
				} catch (IOException e) {
					model.addAttribute("message", "Error while uploading file on server. " + e.getMessage());
				model.addAttribute("book", book);
				return "add";
			}
		} else {
			imgUri = book.getImageURI();
		}
		
		Book newBook = new Book(book.getId(), name, author, desc, imgUri);
		bookManager.updateBook(newBook);
		
		
		return "redirect:/books/" + newBook.getId();
	}

	
	public void setBookManager(BookManager bookManager){
		this.bookManager = bookManager;
		
	}
	
	@RequestMapping(value = "/books/{id}/delete")
	public String bookDelete(@PathVariable("id") int id){
		
		Book book = bookManager.getBookById(id);

		File oldImageFile = new File("C:/webapp/resources/img/" + book.getImageURI());
		if(oldImageFile.exists())
			oldImageFile.delete();
		
		bookManager.deleteBook(book);
		
		return "redirect:/books";
	}

}
