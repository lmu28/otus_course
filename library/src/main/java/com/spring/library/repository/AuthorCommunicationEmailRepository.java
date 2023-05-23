package com.spring.library.repository;

import com.spring.library.domain.AuthorCommunicationEmail;

public interface AuthorCommunicationEmailRepository {


    AuthorCommunicationEmail save(AuthorCommunicationEmail communicationEmail);
    AuthorCommunicationEmail findById(int id);

    void updateEmailById(int id, String name);
    void deleteById(int id);

}
