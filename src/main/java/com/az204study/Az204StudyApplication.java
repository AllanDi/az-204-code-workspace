package com.az204study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Az204StudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(Az204StudyApplication.class, args);
    }
}
