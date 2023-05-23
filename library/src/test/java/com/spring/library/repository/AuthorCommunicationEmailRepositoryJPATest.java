package com.spring.library.repository;

import com.spring.library.domain.Author;
import com.spring.library.domain.AuthorCommunicationEmail;
import com.spring.library.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@ComponentScan("com.spring.library.repository")
class AuthorCommunicationEmailRepositoryJPATest {

    public static final int FIRST_COMMUNICATION_EMAIL_ID = 1;
    public static final String NEW_EMAIL_NAME = "new_email@Gmail.com";
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    AuthorCommunicationEmailRepository communicationEmailRepository;

    @Test
    void save() {

    }

    @Test
    void findById() {
        assertThat(entityManager.find(AuthorCommunicationEmail.class, FIRST_COMMUNICATION_EMAIL_ID)).
                isEqualTo(communicationEmailRepository.findById(FIRST_COMMUNICATION_EMAIL_ID));
    }

    @Test
    void updateEmailById() {
        communicationEmailRepository.updateEmailById(FIRST_COMMUNICATION_EMAIL_ID, NEW_EMAIL_NAME);
        assertThat(entityManager.find(AuthorCommunicationEmail.class, FIRST_COMMUNICATION_EMAIL_ID)
                .getName()).isEqualTo(NEW_EMAIL_NAME);
    }

    @Test
    void deleteById() {
        assertThat(entityManager.find(AuthorCommunicationEmail.class, FIRST_COMMUNICATION_EMAIL_ID)).isNotNull();
        communicationEmailRepository.deleteById(FIRST_COMMUNICATION_EMAIL_ID);
        entityManager.clear();
        assertThat(entityManager.find(AuthorCommunicationEmail.class,FIRST_COMMUNICATION_EMAIL_ID)).isNull();
    }
}