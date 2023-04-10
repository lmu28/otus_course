package com.testing.app.springboot.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import com.testing.app.springboot.domain.Question;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionDaoCSV implements QuestionDao {

    private ClassPathResource classPathResource;
   // private QuestionService questionService;


    public QuestionDaoCSV(ClassPathResource classPathResource) {
        this.classPathResource = classPathResource;
        //this.questionService = questionService;
    }

    public ClassPathResource getClassPathResource() {
        return classPathResource;
    }

    public void setClassPathResource(ClassPathResource classPathResource) {
        this.classPathResource = classPathResource;

    }

    @Override
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(classPathResource.getInputStream())))) {
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                questions.add(getQuestionFromLine(nextLine));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return questions;
    }


    private  Question getQuestionFromLine(String[] parts) {
        return new Question(parts[0],parts[1],parts[2],parts[3],parts[4]);
    }
}
