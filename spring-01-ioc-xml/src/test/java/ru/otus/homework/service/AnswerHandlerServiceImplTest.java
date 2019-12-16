package ru.otus.homework.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnswerHandlerServiceImplTest {
    private AnswerHandlerService handlerService;

    @BeforeEach
    public void setUp() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        handlerService = applicationContext.getBean(AnswerHandlerService.class);
    }

    @Test
    public void checkTwoAnswers() {
        Assertions.assertThat(handlerService.validateAnswer("TroLolo", "trololo ")).isTrue();


    }

}