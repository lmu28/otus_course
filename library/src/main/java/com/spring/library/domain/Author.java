package com.spring.library.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "author")


public class Author {
    @Id
    String id;


    String name;


  //  private AuthorCommunicationEmail communicationEmail;



    @DBRef
    private List<Book> books;


    public Author() {
        if (books == null) books = new ArrayList<>();
    }

    public Author(String id, String name, AuthorCommunicationEmail communicationEmail, List<Book> books) {
        this.id = id;
        this.name = name;
      //  this.communicationEmail = communicationEmail;
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
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

  //  public AuthorCommunicationEmail getCommunicationEmail() {
   //     return communicationEmail;
   // }

  //  public void setCommunicationEmail(AuthorCommunicationEmail authorEmail) {
  //      this.communicationEmail = authorEmail;
  //  }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
    //            ", name='" + communicationEmail.getName() + '\'' +
                ", books=" + books +
                '}';
    }
}
