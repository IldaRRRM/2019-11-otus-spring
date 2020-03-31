package ru.otus.homework.springlibrary.dto;

import lombok.Data;

@Data
public class GenreDto {

    private String id;
    private String name;

    public GenreDto(String name) {
        this.name = name;
    }
}
