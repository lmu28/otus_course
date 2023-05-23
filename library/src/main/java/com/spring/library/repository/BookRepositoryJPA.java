package com.spring.library.repository;


import com.spring.library.domain.Book;
import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Transactional
@Repository
public class BookRepositoryJPA implements BookRepository {

    @PersistenceContext
    EntityManager entityManager;


    private final CommentRepository commentRepository;

    public BookRepositoryJPA(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Book save(Book book) {
        if(book.getId() < 0){
             entityManager.persist(book);
            return book;
        }else{
            return entityManager.merge(book);
        }
    }


    @Override
    public Book findById(int id) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("book-comment-author-entity-graph");
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b " +
                "where b.id = :id", Book.class);
        query.setParameter("id",id);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getSingleResult();
    }

    @Override
    public List<Book> findAll() {


        EntityGraph<?> entityGraph = entityManager.getEntityGraph("book-comment-author-entity-graph");
        Query query = entityManager.createQuery("select b from Book b");
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public List<Book> findByName(String name) {
        TypedQuery<Book> query =
                entityManager.createQuery("select b from Book b where b.name = :name",Book.class);
        query.setParameter("name",name);
        return query.getResultList();
    }

    @Override
    public void updateNameById(int id, String name) {
        Query query =
                entityManager.createQuery("update Book b set b.name = :name where b.id = :id");
        query.setParameter("id",id);
        query.setParameter("name",name);
        query.executeUpdate();
    }

    @Override
    public void deleteById(int id) {

        //commentRepository.deleteByBookId(id);
        Query query =
                entityManager.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id",id);
        query.executeUpdate();
    }

    @Override
    public void deleteByAuthorId(int authorId) {
      entityManager.createQuery("delete from  Book b" +
                " where b.author.id = :author_id")
                .setParameter("author_id",authorId).executeUpdate();
    }
}
