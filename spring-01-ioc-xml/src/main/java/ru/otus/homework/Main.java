package ru.otus.homework;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.service.impl.ExamServiceImpl;

import java.io.IOException;

@Slf4j
public class Main {

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        ExamServiceImpl examService = applicationContext.getBean(ExamServiceImpl.class);
        examService.startExam();

    }
}
