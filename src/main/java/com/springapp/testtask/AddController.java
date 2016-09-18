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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springapp.testtask.domain.Book;
import com.springapp.testtask.repository.BookDAO;
import com.springapp.testtask.service.BookManager;

@Controller
public class AddController {

	@Autowired
	private BookManager bookManager;
	
	public void setBookManager(BookManager bookManager){
		this.bookManager = bookManager;
	}
	
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addBook(@RequestParam(value="nameBook", required=true) String name,
			@RequestParam(value="nameAuthor", required=true) String author,
			@RequestParam(value="desc", required=true) String desc,
			@RequestParam(value="img", required=true) MultipartFile img,
			Model model){
		
		Book book = new Book(name, author, desc, null);
		
		if(name.isEmpty() || author.isEmpty() || desc.isEmpty() || img.isEmpty()){
			
			model.addAttribute("message", "All fields must be filled");
			model.addAttribute("book", book);
			
			return "add";
			
		}
		String contentType = img.getContentType();
		
		if(!img.getContentType().equals("image/jpeg")){
			
			model.addAttribute("message", "Book cover must be *.jpg file");
			model.addAttribute("book", book);
			
			return "add";
		}
			
			try {
				byte[] bytes = img.getBytes();
				File dir = new File("C:/webapp/resources/img");
				
				if(!dir.exists())
					dir.mkdirs();
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
				Date date = new Date();
				String fileName = dateFormat.format(date) + "." + img.getOriginalFilename().split("\\.")[1];
;

				File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
				
				BufferedOutputStream out = new BufferedOutputStream( new FileOutputStream(serverFile));
				out.write(bytes);
				out.close();
								
				book.setImageURI(fileName);

			} catch (IOException e) {
				model.addAttribute("message", "Error while uploading file on server. " + e.getMessage());
				model.addAttribute("book", book);
				return "add";
			}
			
		
		bookManager.addBook(book);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/add")
	public String add(){

		return "add";
	}
	
	
}
