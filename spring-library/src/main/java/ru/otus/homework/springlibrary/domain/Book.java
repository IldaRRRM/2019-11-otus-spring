package ru.otus.homework.springlibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document(collection = "books")
@Data
public class Book {

    @Id
    private String id;
    private String name;
    private Integer releaseYear;
    private List<Author> authors = new ArrayList<>();
    private List<Genre> genres = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();

    public Book(String id, String name, int releaseYear) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
    }

    public Book(String name, int releaseYear, List<Author> authors, List<Genre> genres) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.authors = authors;
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "id = " + getId() +
                "; bookName = " + getName() +
                "; bookAuthors = " + getAuthors() +
                "; genres of Book " + getGenres();
    }
}


