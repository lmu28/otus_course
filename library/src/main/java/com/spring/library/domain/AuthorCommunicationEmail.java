package com.spring.library.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "author_communication_email")
public class AuthorCommunicationEmail {

    @Id
    String id;

    String name;


    private Author author;

    public AuthorCommunicationEmail() {
    }



    public AuthorCommunicationEmail(String id, String name, Author author) {
        this.id = id;
        this.name = name;
        this.author = author;
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

    @Override
    public String toString() {
        return "AuthorCommunicationEmail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author.getName() +
                '}';
    }
}
