package com.testing.app.springboot.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.Locale;


@Configuration
//@ComponentScan(basePackages = "com.testing.app.springboot")
public class Config {

    private  MessageSource ms;
    private ApplicationProperties ap;


    public Config(MessageSource ms, ApplicationProperties ap) {
        this.ap = ap;
        this.ms = ms;

    }

    @Bean
    public ClassPathResource classPathResource(){
        String path = ms.getMessage("file.path",new String[]{},ap.getLocale());
        return new ClassPathResource(path);
    }

}
