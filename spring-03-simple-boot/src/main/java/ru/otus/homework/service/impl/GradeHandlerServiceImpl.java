package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.service.GradeHandlerService;

@Service
public class GradeHandlerServiceImpl implements GradeHandlerService {

    @Override
    public boolean gradeHandle(Integer correctAnswers, Integer examPassAnswersCount) {
        return correctAnswers >= examPassAnswersCount;
    }
}
