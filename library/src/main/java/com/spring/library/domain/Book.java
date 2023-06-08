package com.spring.library.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "book")
public class Book {

    @Id
    private String id;

    private String name;



    @DBRef
    private Author author;




@DBRef
    private List<Genre> genres;



    public Book() {
    }

    public Book(String id, String name, List<Comment> comments, Author author, List<Genre> genres) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genres = genres;
    }

    public Book(String id, String name) {
        this.id = id;
        this.name = name;
        if (genres == null) genres = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        String authorName = " ";
        if (getAuthor() != null) authorName = getAuthor().name;
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + authorName +
                ", genres=" + genres.stream().map(g -> g.getName()).collect(Collectors.toList()) +
                '}';
    }
}
