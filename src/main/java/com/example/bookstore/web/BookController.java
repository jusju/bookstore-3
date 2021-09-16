package com.example.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;

	public BookController(BookRepository repository) {
		this.repository = repository;
	}

	@RequestMapping("/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}

	@PostMapping("/add")
	public String postBook(String title, String author, String isbn, String year) {
		Book newbook = new Book(isbn, title, author, year);
		repository.save(newbook);
		return "redirect:/booklist";
	}

	@GetMapping("/delete/{no}")
	public String deleteBook(@PathVariable String no) {
		long id = Long.parseLong(no);
	    repository.deleteById(id);
	    return "redirect:/booklist";
	}
}