package org.student.testing.service;

import org.student.testing.domain.Question;

import java.util.List;

public interface QuestionService {

    Question getQuestionFromLine(String[] parts);
}
