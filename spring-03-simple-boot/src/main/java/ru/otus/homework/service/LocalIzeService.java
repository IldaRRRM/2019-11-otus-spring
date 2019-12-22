package ru.otus.homework.service;

import java.util.Locale;

public interface LocalIzeService {

    String getLocalizeParamValue(String resourceParam);

    String getLocalizeParamValue(String resourceParam, Locale locale);

    String getLocalizeParamValue(String resourceParam, Object[] args);

    String getLocalizeParamValue(String resourceParam, Object[] args, Locale locale);
}
