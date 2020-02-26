package ru.otus.homework.springlibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "genres")
@Getter
public class Genre {

    @Id
    private String id;
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
