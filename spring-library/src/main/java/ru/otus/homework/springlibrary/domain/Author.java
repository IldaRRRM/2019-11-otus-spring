package ru.otus.homework.springlibrary.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Document(collection = "authors")
public class Author {

    @Id
    private String id;
    private String name;
    private String country;

    public Author(String name) {
        this.name = name;
    }

    public Author(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}


