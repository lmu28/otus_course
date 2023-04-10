package com.testing.app.springboot.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class PropertiesForTesting {

    private MessageSource messageSource;
    private ApplicationProperties applicationProperties;
    private String greeting;
    private String option1;
    private String option2;
    private String option3;
    private String askCorrectAnswer;
    private String result;



    public PropertiesForTesting(MessageSource messageSource, ApplicationProperties applicationProperties) {
        this.messageSource = messageSource;
        this.applicationProperties = applicationProperties;
        fillTestingProp();
    }

    private void fillTestingProp(){
        Locale locale = applicationProperties.getLocale();
        greeting = messageSource.getMessage("testing.service.greeting",new Object[]{},locale);
        option1 = messageSource.getMessage("testing.service.option1",new Object[]{},locale);
        option2 = messageSource.getMessage("testing.service.option2",new Object[]{},locale);
        option3 = messageSource.getMessage("testing.service.option3",new Object[]{},locale);
        askCorrectAnswer = messageSource.getMessage("testing.service.ask-to-input-correct",new Object[]{},locale);
        result = messageSource.getMessage("testing.service.result",new Object[]{},locale);

    }

    public String getGreeting() {
        return greeting;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getAskCorrectAnswer() {
        return askCorrectAnswer;
    }

    public String getResult() {
        return result;
    }
}
