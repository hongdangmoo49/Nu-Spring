package com.sparta.springnoob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringnoobApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringnoobApplication.class, args);
    }

}
