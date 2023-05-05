package com.spring.library.domain;

import jakarta.persistence.*;

import java.util.List;


@Entity(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;



    @OneToMany(targetEntity = Comment.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    List<Comment> comments;

    @ManyToOne(targetEntity = Author.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;



    @ManyToMany(targetEntity = Genre.class, cascade = CascadeType.ALL)
    @JoinTable(name="book_genre", joinColumns = @JoinColumn(name="book_id")
            ,inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;



    public Book() {
    }
    public Book(int id, String name, Author author, List<Comment> comments, List<Genre> genres) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.comments = comments;
        this.genres = genres;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", genres=" + genres +
                '}' + "\n\n";
    }
}
