package ru.otus.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public class Student {

    private final Map<String, String> grades = new HashMap<>();
    private final String firstName;
    private final String SecondName;

}
