package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.service.GradeHandlerService;

@Service
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

    @Override
    public boolean gradeHandle(Integer correctAnswers, Integer examPassAnswersCount) {
        return correctAnswers >= examPassAnswersCount;
    }
}
