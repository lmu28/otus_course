package com.spring.library.repository;

import com.spring.library.domain.Book;
import javax.persistence.*;

import java.util.List;

public interface BookRepository {

    Book save(Book book);
    Book findById(int id);
    List<Book> findAll();

    List<Book> findByName(String name);
    void updateNameById(int id,String name );

    void deleteById(int id);

    void deleteByAuthorId(int authorId);




}
