package com.spring.library.repository;

import com.spring.library.domain.Author;
import com.spring.library.dto.AuthorBooks;

import java.util.List;

public interface AuthorRepository {

    Author save(Author author);
    Author findById(int id);
    void deleteById(int id);


    int calcAuthorCount();
    List<Author> findAuthorWithGivenBooks(String bookName1, String bookName2);
    List<Author> findAuthorWithGivenNames(List<String> names);
    List<Author> findAuthorsWithBookCountMoreThanAllEmployees();
    List<AuthorBooks> findAuthorBooksCount();


}
