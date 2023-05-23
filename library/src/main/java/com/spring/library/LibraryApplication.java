package com.spring.library;


import com.spring.library.domain.Author;
import com.spring.library.domain.Book;
import com.spring.library.domain.Comment;
import com.spring.library.domain.Genre;
import com.spring.library.repository.BookRepository;
import com.spring.library.repository.BookRepositoryJPA;
import javax.persistence.*;

import com.spring.library.repository.CommentRepository;
import org.h2.tools.Console;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class LibraryApplication {


	//@Transactional
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(LibraryApplication.class, args);
//		BookRepository bookRepository = context.getBean(BookRepository.class);
//		Author author = new Author("Иван");
//		Book book = new Book("Книжка",null,new ArrayList<>());
//		book.setAuthor(author);
//		Genre genre1 =new Genre("Жанр1");
//		Genre genre2=new Genre("Жанр2");
//		Genre genre3 =new Genre("Жанр3");
//		book.getGenres().add(genre1);
//		book.getGenres().add(genre2);
//		book.getGenres().add(genre3);
//		bookRepository.save(book);
//
//		CommentRepository commentRepository = context.getBean(CommentRepository.class);
//		BookRepository bookRepository = context.getBean(BookRepository.class);

		//Book book1 = bookRepository.findById(1);
		//Comment newComment = new Comment(0,"Очень интересная книга!",book1);
		//newComment = commentRepository.save(newComment);

//		commentRepository.updateBodyById(1,"123");
//		System.out.println(commentRepository.findById(1));

		//System.out.println(bookRepository.findById(1));








		Console.main(args);

	}

}
