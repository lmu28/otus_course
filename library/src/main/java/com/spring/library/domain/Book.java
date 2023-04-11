package com.spring.library.domain;

public class Book {

    int id;
    String name;
    int authorId;
    int genreId;

    public Book(int id, String name, int authorId, int genreId) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.genreId = genreId;
    }

    public Book(String name, int authorId, int genreId) {
        this.name = name;
        this.authorId = authorId;
        this.genreId = genreId;
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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorId=" + authorId +
                ", genreId=" + genreId +
                '}';
    }
}
