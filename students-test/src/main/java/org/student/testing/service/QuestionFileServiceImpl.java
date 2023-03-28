package org.student.testing.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.student.testing.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class QuestionFileServiceImpl implements QuestionFileService {

    private ClassPathResource classPathResource;
    private QuestionService questionService;


    public QuestionFileServiceImpl(ClassPathResource classPathResource, QuestionService questionService) {
        this.classPathResource = classPathResource;
        this.questionService = questionService;
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
            reader.readNext(); // read header of file
            while ((nextLine = reader.readNext()) != null) {
                questions.add(questionService.getQuestionFromLine(nextLine));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return questions;
    }
}
