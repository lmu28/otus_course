package com.spring.library.service;

import com.spring.library.dao.BookDao;
import com.spring.library.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    BookDao bookDao;


    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }



    public Book getById(int id){
        return bookDao.getById(id);
    }

    public void insert(Book book){
        bookDao.insert(book);
    }

    public List<Book> getAll(){
        return bookDao.getAll();
    }
    public void update(Book book){
        bookDao.update(book);
    }
    public void deleteById(int id){
        bookDao.deleteById(id);

    }


}
