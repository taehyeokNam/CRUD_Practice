package com.example.crud_practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CrudPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudPracticeApplication.class, args);
    }

}
