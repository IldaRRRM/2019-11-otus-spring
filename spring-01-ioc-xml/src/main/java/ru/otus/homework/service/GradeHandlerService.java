package ru.otus.homework.service;

public interface GradeHandlerService {

    String gradeHandle(Integer correctAnswers);

    boolean gradeHandle(Integer correctAnswers, Integer examPassAnswersCount);

}
