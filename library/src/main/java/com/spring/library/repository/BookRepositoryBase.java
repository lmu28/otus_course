package com.spring.library.repository;

import com.spring.library.domain.Book;
import com.spring.library.dto.BookGenresCount;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepositoryBase {
    List<BookGenresCount> getBookGenresCount();

}




