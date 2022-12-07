package com.example.covidline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CovidLineApplication {

    public static void main(String[] args) {
        SpringApplication.run(CovidLineApplication.class, args);
    }

}
