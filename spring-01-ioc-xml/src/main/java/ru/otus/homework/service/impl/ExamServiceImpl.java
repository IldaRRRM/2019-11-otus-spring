package ru.otus.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.otus.homework.domain.CsvExamData;
import ru.otus.homework.service.AnswerHandlerService;
import ru.otus.homework.service.ExamService;
import ru.otus.homework.service.GradeHandlerService;
import ru.otus.homework.service.ReadFileService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class ExamServiceImpl implements ExamService {

    private final ReadFileService readFileService;
    private final GradeHandlerService gradeHandlerService;
    private final AnswerHandlerService answerHandlerService;

    @Override
    public void startExam() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            log.info("Введите имя : ");
            String studentName = reader.readLine();
            log.info("Введите фамилию : ");
            String studentSecondName = reader.readLine();
            List<CsvExamData> examData = (List<CsvExamData>) readFileService.readFromInnerFields();
            int rightAnswer = 0;
            for (CsvExamData currentExamData : examData) {
                log.info("Вопрос : {}", currentExamData.getQuestion());
                String studentAnswer = reader.readLine();
                rightAnswer = answerHandlerService.validateAnswer(studentAnswer, currentExamData.getAnswer()) ?
                        ++rightAnswer : rightAnswer;
            }

            String studentGrade = gradeHandlerService.gradeHandle(rightAnswer);
            log.info("Оценка за тест: {}", studentGrade);
        }
    }
}
