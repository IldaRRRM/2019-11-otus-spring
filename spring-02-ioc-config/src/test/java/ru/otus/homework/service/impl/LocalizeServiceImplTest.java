package ru.otus.homework.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.homework.service.LocalIzeService;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DisplayName("Проверка локализации")
@ExtendWith(MockitoExtension.class)
public class LocalizeServiceImplTest {

    private LocalIzeService localIzeService;

    @BeforeEach
    public void setUp() {

        ReloadableResourceBundleMessageSource ms
                = new ReloadableResourceBundleMessageSource();
        ms.setBasename("bundle");
        ms.setDefaultEncoding("UTF-8");
        localIzeService = new LocalizeServiceImpl(ms);
    }

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