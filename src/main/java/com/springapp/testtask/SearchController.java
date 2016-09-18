package com.springapp.testtask;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springapp.testtask.domain.Book;
import com.springapp.testtask.service.BookManager;

@Controller
public class SearchController {

	@Autowired
	private BookManager bookManager;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Locale locale, Model model){
		
		return "search";
		
	}
	
	@RequestMapping(value= "/searchResult")
	public String searchResults(@RequestParam(value="searchMethod", required = false, defaultValue = "name") String searchMethod,
			@RequestParam(value="value", required=true ) String name, Model model){
		
		List<Book> result = bookManager.getBooks(searchMethod, name);
		
		model.addAttribute("searchMethod", searchMethod);
		model.addAttribute("searchValue", name);
		model.addAttribute("booksList", result);
		model.addAttribute("resultNum", result.size());
		
		return "searchResult";
	}
	
	public void setBookManager(BookManager bookManager){
		this.bookManager = bookManager;
		
	}

}
