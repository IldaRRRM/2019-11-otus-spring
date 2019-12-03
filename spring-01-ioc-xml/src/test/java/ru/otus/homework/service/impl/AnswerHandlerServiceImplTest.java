package ru.otus.homework.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.service.AnswerHandlerService;

import static org.hamcrest.core.Is.is;

public class AnswerHandlerServiceImplTest {
    private AnswerHandlerService handlerService;

    @Before
    public void setUp() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        handlerService = applicationContext.getBean(AnswerHandlerService.class);
    }

    @Test
    public void checkTwoAnswers() {
        Assert.assertThat(handlerService.validateAnswer("TroLolo", "trololo "), is(true));

    }

}