package ru.otus.homework.springbatch.domain.mongo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Document(collection = "authors")
public class MongoAuthor {

    @Id
    private String id;
    @Setter
    private String name;
    @Setter
    private String country;

    public MongoAuthor(String name) {
        this.name = name;
    }

    public MongoAuthor(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
