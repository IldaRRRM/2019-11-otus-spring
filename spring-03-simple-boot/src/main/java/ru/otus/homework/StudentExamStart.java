package ru.otus.homework;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import ru.otus.homework.service.ExamService;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(value = "exam.prom.status", havingValue = "true")
public class StudentExamStart implements CommandLineRunner {

    private final ExamService examService;

    @Override
    public void run(String... args) throws Exception {
        examService.startExam();
    }

    public static void main(String[] args) {
        SpringApplication.run(StudentExamStart.class, args);
    }
}
