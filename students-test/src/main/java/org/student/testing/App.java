package org.student.testing;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.student.testing.config.Config;
import org.student.testing.service.QuestionFileService;
import org.student.testing.service.QuestionFileServiceImpl;
import org.student.testing.service.TestingService;

import java.util.Scanner;




public class App 
{
    static final Scanner scanner = new Scanner(System.in);
    public static void main( String[] args ) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        //ClassPathResource questionsResource = context.getBean("questionsResource", ClassPathResource.class);


        QuestionFileService fileService = context.getBean(QuestionFileServiceImpl.class);
        TestingService testingService = context.getBean(TestingService.class);

        testingService.startTest(fileService.getAllQuestions());
        //System.out.println(fileService.getAllQuestions());







        context.close();

    }


}
