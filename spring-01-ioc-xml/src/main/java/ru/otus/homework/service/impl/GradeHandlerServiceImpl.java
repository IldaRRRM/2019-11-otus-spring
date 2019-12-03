package ru.otus.homework.service.impl;

import ru.otus.homework.service.GradeHandlerService;

public class GradeHandlerServiceImpl implements GradeHandlerService {
    @Override
    public String gradeHandle(Integer correctAnswers) {
        switch (correctAnswers) {
            case 5:
                return "Отл";
            case 4:
                return "Хор";
            case 3:
                return "Удовл";
            default:
                return "Неудв";
        }

    }
}
