package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework.service.LocalIzeService;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LocalizeServiceImpl implements LocalIzeService {

    private final MessageSource messageSource;

    @Value("#{ systemProperties['user.language'] }")
    private Locale currentLocale;

    @Override
    public String getLocalizeParamValue(String resourceParam) {
        return messageSource.getMessage(resourceParam, null, currentLocale);
    }

    @Override
    public String getLocalizeParamValue(String resourceParam, Object[] args) {
        return messageSource.getMessage(resourceParam, args, currentLocale);
    }

    @Override
    public String getLocalizeParamValue(String resourceParam, Object[] args, Locale locale) {
        return messageSource.getMessage(resourceParam, args, locale);
    }

    @Override
    public String getLocalizeParamValue(String resourceParam, Locale locale) {
        return messageSource.getMessage(resourceParam, null, locale);
    }

    @Override
    public void setLocale(String locale) {
        this.currentLocale = new Locale(locale);
    }
}
