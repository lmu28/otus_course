package com.spring.library;


import com.spring.library.domain.Author;
import com.spring.library.domain.Book;
import com.spring.library.domain.Genre;
import com.spring.library.repository.AuthorRepository;
import com.spring.library.repository.BookRepository;
import com.spring.library.repository.GenreRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@EnableMongoRepositories("com.spring.library.repository")
@SpringBootApplication
public class LibraryApplication {





	public static void main(String[] args) throws Exception {

		ConfigurableApplicationContext context = SpringApplication.run(LibraryApplication.class, args);
		LibraryApplication la = new LibraryApplication();

		BookRepository bookRepository = context.getBean(BookRepository.class);
		AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
		GenreRepository genreRepository = context.getBean(GenreRepository.class);



		Book book = new Book(null,"The Lord of the Rings");
		Genre genre1 = new Genre(null,"Fantasy");
		Genre genre2 = new Genre(null,"Science Fiction");
		Author author = new Author();
		author.setName("John Ronald");

		book = bookRepository.save(book);
		genre1 = genreRepository.save(genre1);
		genre2 = genreRepository.save(genre2);
		author = authorRepository.save(author);





		book.getGenres().add(genre1);
		book.getGenres().add(genre2);
		book.setAuthor(author);
		author.getBooks().add(book);

		book = bookRepository.save(book);
		genre1 = genreRepository.save(genre1);
		genre2 = genreRepository.save(genre2);
		author = authorRepository.save(author);




		System.out.println("\n\n\n\n\n\n\n");

		//System.out.println("All books " + bookRepository.findAll());
		//System.out.println("All authors " + authorRepository.findAll());
		//System.out.println("All genres " + genreRepository.findAll());

		System.out.println(bookRepository.findByName("The Lord of the Rings"));


		System.out.println("\n\n\n\n\n\n\n");


	}

}
