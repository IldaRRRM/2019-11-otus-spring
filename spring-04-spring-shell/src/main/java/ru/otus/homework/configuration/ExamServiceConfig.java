package ru.otus.homework.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("exam")
public class ExamServiceConfig {

    @Value(value = "${exam.pass.answers.count}")
    private Integer passExamCount;

    @Bean
    Integer passExamCount() {
        return passExamCount;
    }

}
