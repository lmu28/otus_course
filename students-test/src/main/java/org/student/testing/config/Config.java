package org.student.testing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;


@Configuration
@ComponentScan(basePackages = "org.student.testing")
public class Config {

    @Bean
    public ClassPathResource classPathResource(){
        return new ClassPathResource("questions.csv");
    }

}
