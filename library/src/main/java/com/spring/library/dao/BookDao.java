package com.spring.library.dao;


import com.spring.library.domain.Book;

import java.util.List;

public interface BookDao {

    void insert(Book book);
    Book getById(int id);
    List<Book> getAll();
    void update(Book book);
    void deleteById(int id);

}
