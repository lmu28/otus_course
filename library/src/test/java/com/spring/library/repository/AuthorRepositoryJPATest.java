package com.spring.library.repository;

import com.spring.library.domain.Author;
import com.spring.library.domain.AuthorCommunicationEmail;
import com.spring.library.domain.Book;
import com.spring.library.dto.AuthorBooks;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({AuthorRepositoryJPA.class, BookRepositoryJPA.class, CommentRepositoryJPA.class})
class AuthorRepositoryJPATest {

    private static final int FIRST_BOOK_ID = 1;
    private static final int SECOND_BOOK_ID = 2;
    private static final int FIRST_AUTHOR_ID = 1;
    private static final int EXPECTED_AUTHOR_COUNT = 10;
    private static final String FIRST_BOOK_NAME = "Тёмная башня";
    private static final String SECOND_BOOK_NAME = "Братья Карамазовы";
    private static final int ID_AUTHOR_WITH_TWO_BOOKS = 6;

    private static final String FIRST_AUTHOR_NAME = "Илья Кузнецов";
    private static final String SECOND_AUTHOR_NAME = "Павел Сидоров";
    private static final int ID_AUTHOR_WITH_FIRST_NAME = 4;
    private static final int ID_AUTHOR_WITH_SECOND_NAME = 10;
    private static final List<Integer> COUNT_OF_BOOKS_FOR_EACH_AUTHOR = List.of(4, 4, 3, 3, 3, 3, 3, 3, 3, 1);
    private static final int ID_AUTHOR_WITH_BOOK_COUNT_MORE_THAN_ALL_AUTHORS_1 = 1;
    private static final int ID_AUTHOR_WITH_BOOK_COUNT_MORE_THAN_ALL_AUTHORS_2 = 2;
    private static final int ID_AUTHOR_WITH_BOOK_COUNT_MORE_THAN_ALL_AUTHORS_3 = 3;


    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    TestEntityManager entityManager;


    @Test
    void save() {
        Book book1 = new Book("Book1", new Author(), new ArrayList<>(), new ArrayList<>());
        Book book2 = new Book("Book2", new Author(), new ArrayList<>(), new ArrayList<>());

        Author author = new Author(0, "Author", null, new ArrayList<>());
        AuthorCommunicationEmail communicationEmail = new AuthorCommunicationEmail(0, "bestAuthor@gmail.com", null);
        author.setCommunicationEmail(communicationEmail);
        communicationEmail.setAuthor(author);

        book1.setAuthor(author);
        book2.setAuthor(author);
        author.getBooks().add(book1);
        author.getBooks().add(book2);
        author = authorRepository.save(author);
        int actualID = author.getId();
        String actualName = author.getName();
        String actualBooks = author.getBooks().toString();
        entityManager.flush();
        entityManager.detach(author);
        assertThat(entityManager.find(Author.class, actualID))
                .matches(a -> a.getId() == actualID)
                .matches(a -> a.getName().equals(actualName))
                .matches(a -> a.getBooks().toString().equals(actualBooks));

        entityManager.detach(book1);
        assertThat(entityManager.find(Book.class, book1.getId())).isNotNull();

    }

    @Test
    void findById() {
        assertThat(entityManager.find(Author.class, FIRST_AUTHOR_ID))
                .isEqualTo(authorRepository.findById(FIRST_AUTHOR_ID));
    }

    @Test
    void deleteById() {
        assertThat(entityManager.find(Author.class, FIRST_AUTHOR_ID)).isNotNull();
        authorRepository.deleteById(FIRST_AUTHOR_ID);
        entityManager.clear();
        assertThat(entityManager.find(Author.class, FIRST_AUTHOR_ID)).isNull();

    }

    @Test
    void calcAuthorCount() {
        assertThat(authorRepository.calcAuthorCount()).isEqualTo(EXPECTED_AUTHOR_COUNT);
    }

    @Test
    void findAuthorWithGivenBooks() {
        Author author = entityManager.find(Author.class, ID_AUTHOR_WITH_TWO_BOOKS);

        assertThat(authorRepository.findAuthorWithGivenBooks(FIRST_BOOK_NAME, SECOND_BOOK_NAME))
                .containsExactly(author);


    }

    @Test
    void findAuthorWithGivenFirstNames() {
        Author author1 = entityManager.find(Author.class, ID_AUTHOR_WITH_FIRST_NAME);
        Author author2 = entityManager.find(Author.class, ID_AUTHOR_WITH_SECOND_NAME);

        assertThat(authorRepository.findAuthorWithGivenNames(List.of(FIRST_AUTHOR_NAME, SECOND_AUTHOR_NAME)))
                .containsExactly(author1, author2);
    }

    @Test
    void findAuthorsWithBookCountMoreThanAllEmployees() {
        Author author1 = entityManager.find(Author.class,ID_AUTHOR_WITH_BOOK_COUNT_MORE_THAN_ALL_AUTHORS_1);
        Author author2 = entityManager.find(Author.class,ID_AUTHOR_WITH_BOOK_COUNT_MORE_THAN_ALL_AUTHORS_2);

        assertThat(authorRepository.findAuthorsWithBookCountMoreThanAllEmployees()).hasSize(2)
                .containsExactly(author1,author2);
    }

    @Test
    void findAuthorBooksCount() {
        List<AuthorBooks> authorBooks = authorRepository.findAuthorBooksCount();
        assertThat(authorBooks.stream().map(el -> (int)el.getCountOfBooks())
                .collect(Collectors.toList())).hasSize(EXPECTED_AUTHOR_COUNT).containsExactly(COUNT_OF_BOOKS_FOR_EACH_AUTHOR.toArray(new Integer[0]));
    }
}