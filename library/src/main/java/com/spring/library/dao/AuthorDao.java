package com.spring.library.dao;

import com.spring.library.domain.Author;

public interface AuthorDao {

    Author getById(int id);
    Author getByAllParametersExcludeId(Author author);
    void insert (Author author);
}
