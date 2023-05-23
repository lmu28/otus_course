package com.spring.library.domain;


import javax.persistence.*;
import java.util.List;

@Entity()
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "body")
    String body;

    @ManyToOne(targetEntity = Book.class,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "book_id")
    Book book;



    public Comment() {
    }

    public Comment(int id, String body, Book book) {
        this.id = id;
        this.body = body;
        this.book = book;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return body + "\n";
    }
}
