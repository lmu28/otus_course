package com.testing.app.springboot;

import com.testing.app.springboot.config.ApplicationProperties;
import com.testing.app.springboot.config.ContextInitializer;
import com.testing.app.springboot.dao.QuestionDao;
import com.testing.app.springboot.service.TestingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class TestingAppSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TestingAppSpringBootApplication.class);
        //application.addInitializers(new ContextInitializer());
        ConfigurableApplicationContext context = application.run(args);

        TestingService testingService = context.getBean(TestingService.class);
        testingService.startTesting();
    }



}
