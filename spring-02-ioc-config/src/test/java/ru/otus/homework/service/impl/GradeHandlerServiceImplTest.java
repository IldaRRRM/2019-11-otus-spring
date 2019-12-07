package ru.otus.homework.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.homework.service.GradeHandlerService;

class GradeHandlerServiceImplTest {

    private GradeHandlerService gradeHandlerService;

    @BeforeEach
    public void setUp() {
        gradeHandlerService = new GradeHandlerServiceImpl();
    }

    @Test
    void shouldGetGradeInNumbers() {
        Assertions.assertThat(gradeHandlerService.gradeHandle(5)).isEqualTo("Отл");

    }

    @Test
    void shouldGetGradeInFlagPassOrNot() {
        Assertions.assertThat(gradeHandlerService.gradeHandle(5, 10)).isEqualTo("Экзамен не сдан");
    }
}