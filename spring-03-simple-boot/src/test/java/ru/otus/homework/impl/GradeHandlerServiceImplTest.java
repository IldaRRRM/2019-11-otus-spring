package ru.otus.homework.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.service.GradeHandlerService;

@SpringBootTest
class GradeHandlerServiceImplTest {
    @Autowired
    private GradeHandlerService gradeHandlerService;

    @Test
    void shouldGetGradeInFlagPassOrNot() {
        Assertions.assertThat(gradeHandlerService.gradeHandle(5, 10)).isFalse();
    }
}