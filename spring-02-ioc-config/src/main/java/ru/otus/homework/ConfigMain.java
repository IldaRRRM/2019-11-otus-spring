package ru.otus.homework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.homework.service.ExamService;

import java.io.IOException;

@ComponentScan
public class ConfigMain {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext ctx
                = new AnnotationConfigApplicationContext();
        ctx.register(ConfigMain.class);
        ctx.refresh();

        ctx.getBean(ExamService.class).startExam();
    }
}
