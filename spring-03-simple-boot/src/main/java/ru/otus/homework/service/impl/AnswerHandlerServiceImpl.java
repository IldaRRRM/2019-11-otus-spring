package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.service.AnswerHandlerService;

@Service
public class AnswerHandlerServiceImpl implements AnswerHandlerService {
    @Override
    public boolean validateAnswer(String answer, String expectedAnswer) {
        return answer.trim().toLowerCase().equals(expectedAnswer.trim().toLowerCase());
    }
}
