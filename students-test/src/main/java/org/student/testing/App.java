package org.student.testing;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.student.testing.service.QuestionFileService;
import org.student.testing.service.QuestionFileServiceImpl;

import java.util.Scanner;


public class App 
{
    static final Scanner scanner = new Scanner(System.in);
    public static void main( String[] args ) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        //ClassPathResource questionsResource = context.getBean("questionsResource", ClassPathResource.class);
        QuestionFileService fileService = context.getBean(QuestionFileServiceImpl.class);
        fileService.readAllQuestions();

    }


}
