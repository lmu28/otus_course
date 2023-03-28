package org.student.testing.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class QuestionFileServiceImpl implements QuestionFileService{

    private ClassPathResource classPathResource;

    public QuestionFileServiceImpl(ClassPathResource classPathResource) {
        this.classPathResource = classPathResource;
    }

    public ClassPathResource getClassPathResource() {
        return classPathResource;
    }

    public void setClassPathResource(ClassPathResource classPathResource) {
        this.classPathResource = classPathResource;
    }

    @Override
    public void readAllQuestions() {

        try (CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(classPathResource.getInputStream())))){
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                System.out.println(Arrays.toString(nextLine));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }


    }
}
