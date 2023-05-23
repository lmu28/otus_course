package com.spring.library.repository;

import com.spring.library.domain.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Repository
@Transactional
public class CommentRepositoryJPA implements CommentRepository{


    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Comment save(Comment comment) {
        if(comment.getId() < 0){
            entityManager.persist(comment);
            return comment;
        }else{
            return entityManager.merge(comment);
        }
    }

    @Override
    public Comment findById(int id) {
        return entityManager.find(Comment.class,id);
    }

    @Override
    public void updateBodyById(int id, String body) {
        Query query =
                entityManager.createQuery("update Comment c set c.body = :body where c.id = :id");
        query.setParameter("id",id);
        query.setParameter("body",body);
        query.executeUpdate();
    }

    @Override
    public void deleteById(int id) {

        Query query =
                entityManager.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id",id);
        query.executeUpdate();

    }

    @Override
    public void deleteByBookId(int bookId) {
        entityManager.createQuery("delete from Comment c where c.book.id = :bookId")
                .setParameter("bookId",bookId).executeUpdate();
    }

}
