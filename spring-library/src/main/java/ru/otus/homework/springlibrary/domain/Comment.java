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
@EqualsAndHashCode
@Getter
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;
    @Setter
    private String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

}
