package com.testing.app.springboot.service;

import com.testing.app.springboot.config.PropertiesForTesting;
import com.testing.app.springboot.dao.QuestionDao;
import org.springframework.stereotype.Service;
import com.testing.app.springboot.domain.Question;

import java.util.List;
import java.util.Scanner;


@Service
public class TestingService {

    private final Scanner scanner = new Scanner(System.in);
    private PropertiesForTesting propertiesForTesting;

    private QuestionDao questionDao;

    public TestingService(QuestionDao questionDao,PropertiesForTesting propertiesForTesting) {
        this.propertiesForTesting = propertiesForTesting;
        this.questionDao = questionDao;
    }

    public void startTesting(){
        List<Question> questions = questionDao.getAllQuestions();
        System.out.println(propertiesForTesting.getGreeting());
        String name = scanner.nextLine();
        int valueOfCorrectAnswers = testing(questions);
        endTesting(name,valueOfCorrectAnswers);
    }

    public int  testing(List<Question> questions){

        int valueOfCorrectAnswers = 0;
        for (Question q : questions){
            System.out.println(q.getFormulation());
            System.out.println(propertiesForTesting.getOption1()+" " + q.getA());
            System.out.println(propertiesForTesting.getOption2()+" " + q.getB());
            System.out.println(propertiesForTesting.getOption3()+" " + q.getC());
            System.out.println(propertiesForTesting.getAskCorrectAnswer());
            if (q.getCorrectAnswer().equals(scanner.nextLine().toUpperCase())) valueOfCorrectAnswers++;
            System.out.println("\n");

        }
        return valueOfCorrectAnswers;

    }

    public void endTesting(String name, int valueOfCorrectAnswers){
        System.out.println(name + ", "+ propertiesForTesting.getResult() +" "+ valueOfCorrectAnswers + "/5\n");
    }

}
