package ru.otus.homework.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.NoSuchMessageException;
import ru.otus.homework.service.LocalIzeService;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class LocalizeServiceImplTest {

    @Autowired
    private LocalIzeService localIzeService;

    @Test
    public void shouldLoadInfoFromRuFile() {
        String actual = localIzeService.getLocalizeParamValue("hello.student", new Locale("ru"));
        Assertions.assertThat(actual).isEqualTo("Добро пожаловать на тестирование!");
    }

    @Test
    public void shouldLoadInfoFromEnFile() {
        String actual = localIzeService.getLocalizeParamValue("hello.student", new Locale("en"));
        Assertions.assertThat(actual).isEqualTo("Hello, welcome to test!");
    }

    @Test
    public void shouldThrownByNoSuchMessageException() {
        assertThatThrownBy(() ->
                localIzeService
                        .getLocalizeParamValue("qwe", new Locale("en")))
                .isInstanceOf(NoSuchMessageException.class);
    }
}