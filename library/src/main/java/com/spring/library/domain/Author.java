package com.spring.library.domain;


import javax.persistence.*;
import java.util.List;

@Entity()
@Table(name = "author")

@NamedEntityGraph(name = "author-communication-email-entity-graph",
        attributeNodes = {@NamedAttributeNode("communicationEmail")})
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    String name;

    @OneToOne(targetEntity = AuthorCommunicationEmail.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "communication_email_id")
    private AuthorCommunicationEmail communicationEmail;


    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<Book> books;

    public Author(String name) {
        this.name = name;
    }

//    public Author(int id, String name, List<Book> books) {
//        this.id = id;
//        this.name = name;
//        this.books = books;
//    }

    public Author(int id, String name, AuthorCommunicationEmail authorEmail, List<Book> books) {
        this.id = id;
        this.name = name;
        this.communicationEmail = authorEmail;
        this.books = books;
    }

    public Author() {
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
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

    public AuthorCommunicationEmail getCommunicationEmail() {
        return communicationEmail;
    }

    public void setCommunicationEmail(AuthorCommunicationEmail authorEmail) {
        this.communicationEmail = authorEmail;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", name='" + communicationEmail.getName() + '\'' +
                ", books=" + books +
                '}';
    }
}
