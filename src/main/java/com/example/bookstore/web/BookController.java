package com.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;

	//perussivu /booklist
	@RequestMapping("/booklist")
	public String bookList(Model model) {
		//lisätään kirjat modeliin
		model.addAttribute("books", repository.findAll());
		//booklist.html
		return "booklist";
	}

	// lisäyslomake /add 
	@PostMapping("/add")
	public String postBook(String title, String author, String isbn, String year) {
		//luodaan kirjaolio parametreinä saaduista string-muuttujista
		Book newbook = new Book(isbn, title, author, year);
		//tallennus
		repository.save(newbook);
		//uudelleenohjaus perussivulle
		return "redirect:/booklist";
	}

	// poistolinkki /delete/id
	@GetMapping("/delete/{no}")
	public String deleteBook(@PathVariable String no) {
		//string longiksi
		long id = Long.parseLong(no);
		//poisto
	    repository.deleteById(id);
	    //uudelleenohjaus perussivulle
	    return "redirect:/booklist";
	}
}