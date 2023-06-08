package com.spring.library.dto;

public class BookGenresCount {
    String bookName;
    int countOfGenres;

    public BookGenresCount(String bookName, int countOfGenres) {
        this.bookName = bookName;
        this.countOfGenres = countOfGenres;
    }
}
