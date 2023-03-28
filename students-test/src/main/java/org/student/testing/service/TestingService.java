package org.student.testing.service;

import org.springframework.stereotype.Service;
import org.student.testing.domain.Question;

import java.util.List;
import java.util.Scanner;


@Service
public class TestingService {

    Scanner scanner;

    public TestingService() {
        scanner = new Scanner(System.in);
    }

    public void startTesting(List<Question> questions){
        System.out.println("Hello! input your name: ");
        String name = scanner.nextLine();
        int valueOfCorrectAnswers = testing(questions);
        endTesting(name,valueOfCorrectAnswers);


    }

    public int  testing(List<Question> questions){
        int valueOfCorrectAnswers = 0;
        for (Question q : questions){
            System.out.println(q.getFormulation());
            System.out.println("A: " + q.getA());
            System.out.println("B: " + q.getB());
            System.out.println("C: " + q.getC());
            System.out.println("Input correct option");
            if (q.getCorrectAnswer().equals(scanner.nextLine().toUpperCase())) valueOfCorrectAnswers++;
            System.out.println("\n");

        }
        return valueOfCorrectAnswers;

    }

    public void endTesting(String name, int valueOfCorrectAnswers){
        System.out.println(name + " you have answered " + valueOfCorrectAnswers + "/5\n");
    }

}
