package ru.otus.homework.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.service.AnswerHandlerService;

@SpringBootTest
public class AnswerHandlerServiceImplTest {

    @Autowired
    private AnswerHandlerService handlerService;

    @Test
    public void checkThatAnswersIsNotDependsOnCamelCase() {
        Assertions.assertThat(handlerService.validateAnswer("TroLolo", "trololo")).isTrue();
    }

    @Test
    public void shouldReturnFalseBecauseActualAndExpectedIsDifferent() {
        Assertions.assertThat(handlerService.validateAnswer("TroLolo", "trololos ")).isFalse();
    }

    @Test
    public void checkThatAnswersIsNotDependsOnTrimSpace() {
        Assertions.assertThat(handlerService.validateAnswer("TroLolo", "trololo ")).isTrue();
    }
}
