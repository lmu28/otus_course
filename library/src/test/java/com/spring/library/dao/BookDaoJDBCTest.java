package com.spring.library.dao;

import com.spring.library.dao.ext.BookResultSetExtractor;
import com.spring.library.domain.Author;
import com.spring.library.domain.Book;
import com.spring.library.domain.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для роботы с книгами на основе JDBC")
@JdbcTest
@Import({BookResultSetExtractor.class, BookDaoJDBC.class, GenreDaoJDBC.class, AuthorDaoJDBC.class, Book.class, Author.class, Genre.class})
class BookDaoJDBCTest {

    private final int BOOK_ID = 5;
    private final String BOOK_NAME = "Идиот";
    private final Author BOOK_AUTHOR = new Author(5, "Александра Николаева");
    private final List<Genre> BOOK_GENRES = List.of(new Genre(6, "Драма", 5)
            , new Genre(7, "Роман", 5));

    private final int COUNT_OF_BOOKS = 30;

    private Book testBook;
    private List<Genre> genres;

    @BeforeEach
    void beforeEachMethod() {
        genres = new ArrayList<>();
        genres.add(new Genre("Роман"));
        genres.add(new Genre("Классика"));
        genres.add(new Genre("Исторический"));
        testBook = new Book("Война и мир", new Author("Лев Толстой"), genres);
    }

    @Autowired
    BookDaoJDBC bookDao;

    @Autowired
    GenreDaoJDBC genreDao;

    @DisplayName("Должен возвращать книгу по его id")
    @Test
    void ShouldReturnBookById() {
        Book actualBook = bookDao.getById(BOOK_ID);
        assertThat(actualBook)
                .matches(book -> book.getId() == BOOK_ID)
                .matches(book -> book.getAuthor().toString().equals(BOOK_AUTHOR.toString()))
                .matches(book -> book.getName().equals(BOOK_NAME))
                .matches(book -> book.getGenres().toString().equals(BOOK_GENRES.toString()));

    }

    @DisplayName("Должен возвращать все книги")
    @Test
    void ShouldReturnAllBooks() {
        assertThat(bookDao.getAll()).hasSize(COUNT_OF_BOOKS)
                .allMatch(book -> book.getId() != 0)
                .allMatch(book -> book.getName() != null)
                .allMatch(book -> book.getAuthor() != null);
    }
    @DisplayName("Должен добавлять книгу в базу данных")
    @Test
    void ShouldInsertBookToDataBAse() {
        bookDao.insert(testBook);
        assertThat(bookDao.getById(testBook.getId()))
                .matches(b -> b.getId() == testBook.getId())
                .matches(b -> b.getName().equals(testBook.getName()))
                .matches(b -> b.getAuthor().getName().equals(testBook.getAuthor().getName()))

                .matches(b -> b.getGenres().stream().map(g -> g.getName()).collect(Collectors.toList())
                        .toString().equals(testBook.getGenres().stream().map(g -> g.getName()).collect(Collectors.toList())
                        .toString()));


        assertThat(bookDao.getAll()).hasSize(COUNT_OF_BOOKS + 1);
    }
    @DisplayName("Должен обновлять книгу")
    @Test
    void ShouldUpdateABook() {
        testBook.setId(BOOK_ID);
        bookDao.update(testBook);
        assertThat(bookDao.getById(testBook.getId()))
                .matches(b -> b.getId() == testBook.getId())
                .matches(b -> b.getName().equals(testBook.getName()))
                .matches(b -> b.getAuthor().getName().equals(testBook.getAuthor().getName()))

                .matches(b -> b.getGenres().stream().map(g -> g.getName()).collect(Collectors.toList())
                        .toString().equals(testBook.getGenres().stream().map(g -> g.getName()).collect(Collectors.toList())
                                .toString()));

    }
    @DisplayName("Должен обновлять жанры, когда мы обновляем книгу")
    @Test
    void ShouldUpdateGenresWhenBookUpdates(){
        testBook.setId(BOOK_ID);
        bookDao.update(testBook);
        List<Genre> updatedGenres = genreDao.getByBookId(BOOK_ID);
        assertThat(updatedGenres.stream().map(g -> g.getName()).collect(Collectors.toList())
                .toString().equals(testBook.getGenres().stream().map(g -> g.getName()).collect(Collectors.toList())
                        .toString()));
    }
    @DisplayName("Должен удалять книгу")
    @Test
    void ShouldDeleteBookById(){
        bookDao.deleteById(BOOK_ID);
        assertThat(bookDao.getAll()).hasSize(COUNT_OF_BOOKS-1);
        assertThat(bookDao.getById(BOOK_ID)).isNull();
    }
    @DisplayName("Должен удалять все жанры для удаляемой книги")
    @Test
    void ShouldDeleteAllGenresForDeletedBook(){
        bookDao.deleteById(BOOK_ID);
        assertThat(genreDao.getByBookId(BOOK_ID)).isEmpty();
    }

}