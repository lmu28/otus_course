package org.student.testing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

public class Config {

    @Bean
    public ClassPathResource classPathResource(){
        return new ClassPathResource("questions.csv");
    }

}
