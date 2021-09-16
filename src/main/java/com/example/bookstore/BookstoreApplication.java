package com.example.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			Book h = new Book("12345", "Harry Potter ja Viisasten kivi", "J.K. Rowling", "1900");
			Book m = new Book("23456", "Muukalainen", "Diana Gabaldon", "2000");

			repository.save(h);
			repository.save(m);
		};
	}

}
