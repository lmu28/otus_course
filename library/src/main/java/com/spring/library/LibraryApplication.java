package com.spring.library;

import com.spring.library.dao.BookDao;
import com.spring.library.dao.GenreDao;
import com.spring.library.domain.Author;
import com.spring.library.domain.Book;
import com.spring.library.domain.Genre;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(LibraryApplication.class, args);
//		BookDao bookDao = context.getBean(BookDao.class);
//		GenreDao genreDao = context.getBean(GenreDao.class);

		//System.out.println(genreDao.getById(1));
		//System.out.println("Книга 5: " + bookDao.getById(5));
	//	System.out.println("Все книги " + bookDao.getAll());
//		dao.insert(new Book("T",1,1));
//		System.out.println("Книга с индексом 31: " + dao.getById(31));
//		System.out.println("Все книги: " + dao.getAll());
//		dao.deleteById(1);
//		System.out.println("Все книги: " + dao.getAll());
//		dao.update(new Book(2,"test2",2, 2));
//		System.out.println("обновленная книга с индексом 2: " + dao.getById(2));

//		Book book = new Book("book_name",new Author("TEst TEst"),new ArrayList<>());
//		book.getGenres().add(new Genre("genre1"));
//		book.getGenres().add(new Genre("genre2"));
//		book.getGenres().add(new Genre("genre3"));
//		bookDao.insert(book);
		//bookDao.insert(new Book("q",new Author("q"),new ArrayList<>()));
		//bookDao.deleteById(3);
//		Book book = bookDao.getById(3);
//		book.setAuthor(new Author("qwerty"));
//		book.setName("Updated 3 book");
//		book.getGenres().clear();
//		book.getGenres().add(new Genre("bgener1"));
//		book.getGenres().add(new Genre("bgener2"));
//		book.getGenres().add(new Genre("bgener3"));
//		bookDao.update(book);
//		List<Genre> genres = new ArrayList<>();
//		genres.add(new Genre("bgener1", 1));
//		genres.add(new Genre("bgener2", 1));
//		genres.add(new Genre("bgener3", 1));
//		genreDao.insertAll(genres);

		//Console.main(args);

	}

}
