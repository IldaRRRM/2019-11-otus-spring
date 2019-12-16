package ru.otus.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@SpringBootApplication
@ConditionalOnProperty(value = "exam.prom.status", havingValue = "false", matchIfMissing = true)
public class TestAppContext {
    public static void main(String[] args) {
        SpringApplication.run(TestAppContext.class, args);
    }
}
