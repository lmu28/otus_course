package com.spring.library.repository;

import com.spring.library.domain.Author;
import com.spring.library.domain.Book;
import com.spring.library.domain.Comment;
import com.spring.library.domain.Genre;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import({BookRepositoryJPA.class, CommentRepositoryJPA.class})
class BookRepositoryJPATest {

    private final int FIRST_BOOK_ID = 1;
    private final int COUNT_OF_BOOKS = 31;
    private final int COUNT_OF_QUERIES = 2;
    //44


    private final String FIRST_BOOK_NAME = "Мастер и Маргарита";

    private String NEW_NAME_FOR_FIRST_BOOK = "Маргарита и Мастер";


    private Author author;
    private Book book;
    private Genre genre1;
    private Genre genre2;
    private Genre genre3;
    private Comment comment1;
    private Comment comment2;

    @Autowired
    TestEntityManager entityManager;


    @Autowired
    CommentRepository commentRepository;

    @Autowired
    BookRepository bookRepository;


    @BeforeEach
    void init() {
        author = new Author("Petr Nikolayev");
        book = new Book("qwerty", author, new ArrayList<>(), new ArrayList<>());
        genre1 = new Genre(0,"genre1",new ArrayList<>());
        genre2 = new Genre(0,"genre2",new ArrayList<>());
        genre3 = new Genre(0,"genre3",new ArrayList<>());
        comment1 = new Comment(0, "comment1",null);
        comment2 = new Comment(0, "comment2",null);
        book.getGenres().add(genre1);
        book.getGenres().add(genre2);
        book.getGenres().add(genre3);
        book.getComments().add(comment1);
        book.getComments().add(comment2);

        genre1.getBooks().add(book);
        genre2.getBooks().add(book);
        genre3.getBooks().add(book);

        comment1.setBook(book);
        comment2.setBook(book);
        book = bookRepository.save(book);
        entityManager.flush();
        entityManager.detach(book);


    }

    @Test
    void save() {
        Book actualBook = entityManager.find(Book.class, book.getId());
        assertThat(actualBook.getId()).isGreaterThan(0);

        assertThat(actualBook.toString()).isEqualTo(book.toString());
        assertThat(actualBook)
                .matches(b -> b.getName() != null)
                .matches(b -> b.getAuthor() != null)
                .matches((b -> b.getGenres().size() == 3))
                .matches((b -> b.getComments().size() == 2));
    }

    @Test
    void findById() {
        assertThat(entityManager.find(Book.class, FIRST_BOOK_ID)).
                isEqualTo(bookRepository.findById(FIRST_BOOK_ID));
    }

    @Test
    void findAll() {

        SessionFactory sessionFactory =
                entityManager.getEntityManager().getEntityManagerFactory().unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(COUNT_OF_BOOKS)
                .allMatch(book -> book.getId() > 0)
                .allMatch(book -> book.getGenres().size() >= 0)
                .allMatch(book -> book.getAuthor() != null)
                .allMatch(book -> book.getComments().size() >= 0)
                .allMatch(book -> book.getName() != null);

        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(COUNT_OF_QUERIES);
    }

    @Test
    void findByName() {
        entityManager.flush();
        List<Book> actualList = bookRepository.findByName(FIRST_BOOK_NAME);
        Book expectedBook = entityManager.find(Book.class, FIRST_BOOK_ID);
        assertThat(actualList).containsOnlyOnce(expectedBook);
    }

    @Test
    void updateNameById() {
        Book bookOld = entityManager.find(Book.class, FIRST_BOOK_ID);
        entityManager.detach(bookOld); // Книга со старым именем, отсоединенная от контекста

        bookRepository.updateNameById(FIRST_BOOK_ID, NEW_NAME_FOR_FIRST_BOOK);

        Book newBook = entityManager.find(Book.class, FIRST_BOOK_ID);

        assertThat(newBook.getName()).isEqualTo(NEW_NAME_FOR_FIRST_BOOK);


    }

    @Test
    void deleteById() {

        Book deletedBook = entityManager.find(Book.class, FIRST_BOOK_ID);
        assertThat(deletedBook).isNotNull();
        entityManager.detach(deletedBook);
        bookRepository.deleteById(FIRST_BOOK_ID);
        assertThat(entityManager.find(Book.class, FIRST_BOOK_ID)).isNull();
    }


}