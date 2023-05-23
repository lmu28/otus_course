package com.spring.library.domain;


import javax.persistence.*;
import java.util.List;

@Entity()
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    String name;


    @ManyToMany(mappedBy = "genres",cascade = CascadeType.ALL)
//    @JoinTable(name = "book_genre",joinColumns = @JoinColumn(name = "genre_id")
//            ,inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

    public Genre() {
    }

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public Genre(int id, String name, List<Book> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public Genre(String name) {
        this.name = name;

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


    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
