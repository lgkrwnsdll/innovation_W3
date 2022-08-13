package com.sparta.week3_1;

import com.sparta.week3_1.repository.BoardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Week31Application {

    public static void main(String[] args) {
        SpringApplication.run(Week31Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(BoardRepository repository) {
        return (args) -> {

        };
    }

}
