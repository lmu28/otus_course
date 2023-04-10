package com.testing.app.springboot.dao;
import com.testing.app.springboot.domain.Question;
import org.assertj.core.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionDaoCSVTest {

    @Autowired
    ClassPathResource classPathResource;
    @Autowired
    QuestionDaoCSV daoCSV;

    @BeforeEach
    void setUp(){

    }

    @DisplayName("Корректно получен ClassPathResource")
    @Test
    void shouldReturnClassPathResource(){

        assertThat(daoCSV.getClassPathResource()).isEqualTo(classPathResource);

    }

    @DisplayName("Корректно получены вопросы с русской локалью")
    @Test
    @EnabledIf(expression = "#{environment.acceptsProfiles('ru')}", loadContext = true)
    void shouldReturnAllQuestionsWhenSetUpRussianLocale() {
        assertThat(daoCSV.getAllQuestions().toString()).isEqualTo("[Question{formulation='Какое выражение эквивалентно (a+b)^2?', A='a^2 + 2ab + b^2', B='a^2 - 2ab + b^2', C='a^2 + b^2', correctAnswer='А'}, Question{formulation='Чему равно произведение корней уравнения x^2 + 3x - 10 = 0?', A='-10', B='10', C='-3', correctAnswer='А'}, Question{formulation='Какое число является решением уравнения 2x - 5 = 7x + 2?', A='-1/3', B='3/2', C='-7/5', correctAnswer='В'}, Question{formulation='Чему равна сумма углов треугольника?', A='180 градусов', B='90 градусов', C='360 градусов', correctAnswer='А'}, Question{formulation='Как найти среднее арифметическое двух чисел a и b?', A='(a - b)/2', B='(a + b)/2', C='(b - a)/2', correctAnswer='Б'}]");
    }

    @DisplayName("Корректно получены вопросы с английской локалью")
    @Test
    @EnabledIf(expression = "#{environment.acceptsProfiles('en')}", loadContext = true)
    //@EnabledIfEnvironmentVariable(named = "application.locale", matches = "en")
    void shouldReturnAllQuestionsWhenSetUpEnglishLocale() {

        assertThat(daoCSV.getAllQuestions().toString()).isEqualTo("[Question{formulation='Which expression is equivalent to (a+b)^2?', A='a^2 + 2ab + b^2', B='a^2 - 2ab + b^2', C='a^2 + b^2', correctAnswer='A'}, Question{formulation='What is the product of the roots of the equation x^2 + 3x - 10 = 0?', A='-10', B='10', C='-3', correctAnswer='A'}, Question{formulation='What number is the solution to the equation 2x - 5 = 7x + 2?', A='-1/3', B='3/2', C='-7/5', correctAnswer='C'}, Question{formulation='What is the sum of the angles of a triangle?', A='180 degree', B='90 degree', C='360 degree', correctAnswer='A'}, Question{formulation='How to find the arithmetic mean of two numbers a and b?', A='(a - b)/2', B='(a + b)/2', C='(b - a)/2', correctAnswer='B'}]");
    }

    @Test
    void shouldReturnNewQuestion(){
        try {
            Method getQuestionFromLineMethod = (QuestionDaoCSV.class).getDeclaredMethod("getQuestionFromLine",String[].class);
            getQuestionFromLineMethod.setAccessible(true);
            assertThat(getQuestionFromLineMethod.invoke(daoCSV, (Object)new String[]{"Which expression is equivalent to (a+b)^2?", "a^2 + 2ab + b^2", "a^2 - 2ab + b^2", "a^2 + b^2", "A"}).toString())
                    .isEqualTo(new Question("Which expression is equivalent to (a+b)^2?", "a^2 + 2ab + b^2", "a^2 - 2ab + b^2", "a^2 + b^2", "A").toString());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
      //  assertThat(daoCSV.)
    }

}