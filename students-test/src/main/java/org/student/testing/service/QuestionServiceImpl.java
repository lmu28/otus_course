package org.student.testing.service;

import org.springframework.stereotype.Service;
import org.student.testing.domain.Question;


@Service
public class QuestionServiceImpl implements QuestionService {

    @Override
    public Question getQuestionFromLine(String[] parts) {
        return new Question(parts[0],parts[1],parts[2],parts[3],parts[4]);
    }
}
