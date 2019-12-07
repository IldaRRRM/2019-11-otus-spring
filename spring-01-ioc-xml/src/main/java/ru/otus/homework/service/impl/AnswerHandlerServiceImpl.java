package ru.otus.homework.service.impl;

import ru.otus.homework.service.AnswerHandlerService;

public class AnswerHandlerServiceImpl implements AnswerHandlerService {
    @Override
    public boolean validateAnswer(String answer, String expectedAnswer) {
        return answer.trim().toLowerCase().equals(expectedAnswer.trim().toLowerCase());
    }
}
