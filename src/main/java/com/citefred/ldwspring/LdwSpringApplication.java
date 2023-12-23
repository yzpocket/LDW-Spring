package com.citefred.ldwspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LdwSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(LdwSpringApplication.class, args);
    }

}
