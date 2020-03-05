package ru.otus.homework.springlibrary.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Document(collection = "genres")
public class Genre {

    @Id
    private String id;
    @Setter
    private String name;

    public Genre(String name) {
        this.name = name;
    }

}
