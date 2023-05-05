package com.spring.library.domain;


import jakarta.persistence.*;

@Entity(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "body")
    String body;

    @Column(name = "book_id")
    int book_id;


    public Comment() {
    }

    public Comment(int id, String body, int book_id) {
        this.id = id;
        this.body = body;
        this.book_id = book_id;
    }

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public int getBook_id() {
        return book_id;
    }


    @Override
    public String toString() {
        return body + "\n";
    }
}
