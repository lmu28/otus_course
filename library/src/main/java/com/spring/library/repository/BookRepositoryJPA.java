package com.spring.library.repository;


import com.spring.library.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class BookRepositoryJPA implements BookRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(Book book) {
        if(book.getId() < 0){
            entityManager.persist(book);
        }else{
            entityManager.merge(book);
        }
    }
}
