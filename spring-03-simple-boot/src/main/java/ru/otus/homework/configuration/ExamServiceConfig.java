package ru.otus.homework.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("exam")
public class ExamServiceConfig {

    @Value(value = "classpath:data.csv")
    private Resource resource;
    @Value(value = "${exam.pass.answers.count}")
    private Integer passExamCount;

    @Bean
    public Resource resource() {
        return this.resource;
    }

    @Bean
    Integer passExamCount() {
        return passExamCount;
    }

}
