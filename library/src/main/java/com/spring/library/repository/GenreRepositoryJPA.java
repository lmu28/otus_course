package com.spring.library.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Repository
@Transactional
public class GenreRepositoryJPA implements GenreRepository{

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public void deleteById(int id) {
        entityManager.createQuery("delete from Genre g " +
                "where g.id = :id").setParameter("id",id).executeUpdate();

    }
}
