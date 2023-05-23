package com.spring.library.repository;


import com.spring.library.domain.Comment;

public interface CommentRepository {


    Comment save(Comment comment);
    Comment findById(int id);

    void updateBodyById(int id, String body);
    void deleteById(int id);


    void deleteByBookId(int bookId);
}
