package com.spring.library.domain;

import javax.persistence.*;

@Entity
@Table(name = "author_communication_email")
public class AuthorCommunicationEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    String name;

    @OneToOne(mappedBy = "communicationEmail", cascade = CascadeType.ALL)
    private Author author;

    public AuthorCommunicationEmail() {
    }

    public AuthorCommunicationEmail(int id, String name, Author author) {
        this.id = id;
        this.name = name;
        this.author = author;
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

    @Override
    public String toString() {
        return "AuthorCommunicationEmail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author.getName() +
                '}';
    }
}
