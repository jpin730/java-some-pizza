package com.example.somepizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SomePizzaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SomePizzaApplication.class, args);
    }
}
