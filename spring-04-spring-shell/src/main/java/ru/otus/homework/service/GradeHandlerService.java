package ru.otus.homework.service;

public interface GradeHandlerService {

    boolean gradeHandle(Integer correctAnswers, Integer examPassAnswersCount);

}
