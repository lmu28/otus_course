package com.spring.library.repository;


import com.spring.library.domain.AuthorCommunicationEmail;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Repository
public class AuthorCommunicationEmailRepositoryJPA implements AuthorCommunicationEmailRepository{

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public AuthorCommunicationEmail save(AuthorCommunicationEmail communicationEmail) {
        if(communicationEmail.getId() < 0){
            entityManager.persist(communicationEmail);
            return communicationEmail;
        }else{
            return entityManager.merge(communicationEmail);
        }
    }

    @Override
    public AuthorCommunicationEmail findById(int id) {
        return entityManager.find(AuthorCommunicationEmail.class,id);
    }

    @Override
    public void updateEmailById(int id, String name) {
        Query query =
                entityManager.createQuery("update AuthorCommunicationEmail a_e set a_e.name = :name where a_e.id = :id");
        query.setParameter("id",id);
        query.setParameter("name",name);
        query.executeUpdate();

    }

    @Override
    public void deleteById(int id) {

        Query query =
                entityManager.createQuery("delete from AuthorCommunicationEmail a_e where a_e.id = :id");
        query.setParameter("id",id);
        query.executeUpdate();

    }
}
