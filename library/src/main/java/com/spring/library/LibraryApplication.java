package com.spring.library;


import com.spring.library.domain.Author;
import com.spring.library.domain.Book;
import com.spring.library.domain.Genre;
import com.spring.library.repository.BookRepository;
import com.spring.library.repository.BookRepositoryJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication

public class LibraryApplication {


	@Transactional
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(LibraryApplication.class, args);
		BookRepository bookRepository = context.getBean(BookRepository.class);
		Author author = new Author("Иван");
		Book book = new Book("Книжка",null,new ArrayList<>());
		book.setAuthor(author);
		Genre genre1 =new Genre("Жанр1");
		Genre genre2=new Genre("Жанр2");
		Genre genre3 =new Genre("Жанр3");
		book.getGenres().add(genre1);
		book.getGenres().add(genre2);
		book.getGenres().add(genre3);
		bookRepository.save(book);
		Console.main(args);

	}

}
