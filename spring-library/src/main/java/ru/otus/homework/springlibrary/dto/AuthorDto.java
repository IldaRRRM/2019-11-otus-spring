package ru.otus.homework.springlibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private String id;
    private String name;
    private String country;

    public AuthorDto(String name) {
        this.name = name;
    }
}
