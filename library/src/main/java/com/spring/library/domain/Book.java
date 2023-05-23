package com.spring.library.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

import java.util.List;


@Entity()
@Table(name = "book")

//@NamedEntityGraph(name = "book-comments-entity-graph",
//        attributeNodes = {@NamedAttributeNode("comments")})

@NamedEntityGraph(name = "book-comment-author-entity-graph",
        attributeNodes = {
        @NamedAttributeNode(value ="comments"),
        @NamedAttributeNode(value = "author",subgraph = "author-communication-email-entity-graph")},
        subgraphs = {
                @NamedSubgraph(name = "author-communication-email-entity-graph", attributeNodes = {
                        @NamedAttributeNode("communicationEmail")
                })
        }
)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;



    @OneToMany(mappedBy = "book",cascade = {CascadeType.ALL})

    List<Comment> comments;

    @ManyToOne(targetEntity = Author.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;



    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(targetEntity = Genre.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    public Book(String name, Author author, List<Comment> comments, List<Genre> genres) {
        this.comments = comments;
        this.genres = genres;
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
                ", comments=" + comments +
                ", author=" + author.getName() +
                ", genres=" + genres +
                '}';
    }
}
