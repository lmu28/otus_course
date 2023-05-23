package com.spring.library.dto;

import com.spring.library.domain.Author;

public class AuthorBooks {
    private Author author;
    private long countOfBooks;

    public AuthorBooks(Author author, long countOfBooks) {
        this.author = author;
        this.countOfBooks = countOfBooks;
    }

    public AuthorBooks() {
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public long getCountOfBooks() {
        return countOfBooks;
    }

    public void setCountOfBooks(long countOfBooks) {
        this.countOfBooks = countOfBooks;
    }
}
