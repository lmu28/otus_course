package com.spring.library;

import com.spring.library.dao.BookDao;
import com.spring.library.domain.Book;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;


@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(LibraryApplication.class, args);
		BookDao dao = context.getBean(BookDao.class);
		System.out.println("Все книги: " + dao.getAll());
		dao.insert(new Book("T",1,1));
		System.out.println("Книга с индексом 31: " + dao.getById(31));
		System.out.println("Все книги: " + dao.getAll());
		dao.deleteById(1);
		System.out.println("Все книги: " + dao.getAll());
		dao.update(new Book(2,"test2",2, 2));
		System.out.println("обновленная книга с индексом 2: " + dao.getById(2));

		Console.main(args);

	}

}
