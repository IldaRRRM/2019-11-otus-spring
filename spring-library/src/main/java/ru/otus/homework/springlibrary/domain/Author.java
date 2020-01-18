package ru.otus.homework.springlibrary.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Author {

    private long id;
    private String name;
    private String country;

    public Author(String name) {
        this.name = name;
    }

    public Author(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
