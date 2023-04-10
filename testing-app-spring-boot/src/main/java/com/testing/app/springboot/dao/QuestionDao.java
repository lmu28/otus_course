package com.testing.app.springboot.dao;

import com.testing.app.springboot.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getAllQuestions();
}
