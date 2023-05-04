package com.spring.library.domain;

import java.util.List;

public class Book {

    int id;
    String name;
    Author author;

    List<Genre> genres;

    public Book() {
    }

    public Book(String name, Author author, List<Genre> genres) {
        this.genres = genres;
        this.name = name;
        this.author = author;
    }

    public Book(int id, String name, Author author, List<Genre> genres) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", genres=" + genres +
                '}'+"\n\n";
    }
}
