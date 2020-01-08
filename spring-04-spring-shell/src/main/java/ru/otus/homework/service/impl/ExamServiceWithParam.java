package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.CsvExamData;
import ru.otus.homework.service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@ShellComponent
public class ExamServiceWithParam implements ExamService {

    private final LocalIzeService localIzeService;
    private final ReadFileService readFileService;
    private final AnswerHandlerService answerHandlerService;
    private final GradeHandlerService gradeHandlerService;
    private final Integer examPassCount;

    @Override
    public void startExam() throws IOException, InterruptedException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            log.info(localIzeService.getLocalizeParamValue("student.name"));
            String studentName = reader.readLine();
            log.info(localIzeService.getLocalizeParamValue("student.lastname"));
            String studentSecondName = reader.readLine();
            String pathToResource = localIzeService.getLocalizeParamValue("question.resources");
            List<CsvExamData> examData = (List<CsvExamData>) readFileService.read(pathToResource);
            int rightAnswer = 0;
            String question = localIzeService.getLocalizeParamValue("question.text");

            for (CsvExamData currentExamData : examData) {
                log.info("{} {}", question, currentExamData.getQuestion());
                String studentAnswer = reader.readLine();
                rightAnswer = answerHandlerService.validateAnswer(studentAnswer, currentExamData.getAnswer()) ?
                        ++rightAnswer : rightAnswer;
            }

            String examEstimate = localIzeService.getLocalizeParamValue("exam.estimate");
            String studentGrade = gradeHandlerService.gradeHandle(rightAnswer, examPassCount)
                    ? localIzeService.getLocalizeParamValue("exam.success") :
                    localIzeService.getLocalizeParamValue("exam.failure");
            log.info("{} {}", examEstimate, studentGrade);
        }
    }

    @ShellMethod(value = "Start exam", key = {"start", "st"})
    public void startFromShell() throws IOException, InterruptedException {
        startExam();
    }

    @ShellMethod(value = "Greeting", key = {"hello", "hi"})
    private void printGreeting() {
        log.info("Hello, welcome to exam, please choose your language(ln ru/en)");
    }

    @ShellMethod(value = "Choose locale", key = {"language", "ln"})
    public void setLocale(@ShellOption String locale) {
        if (!locale.equals("ru") || locale.equals("en")) {
            log.info("Sorry, no support for your language");
            log.info(localIzeService.getLocalizeParamValue("hello.student"));
        } else {
            this.localIzeService.setLocale(locale);
            log.info(localIzeService.getLocalizeParamValue("hello.student"));
        }
    }
}
