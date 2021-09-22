package com.example.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository catrep) {
		return (args) -> {
			
			Category a = new Category("Fantasy");
			Category b = new Category("Historical fantasy");
			Category c = new Category("Comedy");
			Category d = new Category("Drama");

			catrep.save(a);
			catrep.save(b);
			catrep.save(c);
			catrep.save(d);

			Book h = new Book("12345", "Harry Potter ja Viisasten kivi", "J.K. Rowling", "2002", a);
			Book m = new Book("23456", "Muukalainen", "Diana Gabaldon", "2000", b);

			repository.save(h);
			repository.save(m);

		};
	}

}
