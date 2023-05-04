package com.spring.library.dao;

import com.spring.library.domain.Genre;

import java.util.List;

public interface GenreDao {

    Genre getById(int id);

    void insert(Genre genre);

    void insertAll(List<Genre> genres);

    void deleteByBookId(int bookID);

    List<Genre> getByBookId(int bookID);

    List<Genre> getAll();

}
