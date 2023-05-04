package com.spring.library.domain;

public class Genre {
    int id;
    String name;
    int book_id;

    public Genre() {
    }

    public Genre(int id, String name, int book_id) {
        this.id = id;
        this.name = name;
        this.book_id = book_id;
    }

    public Genre(String name, int book_id) {
        this.name = name;
        this.book_id = book_id;
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

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", book_id=" + book_id +
                '}';
    }
}
