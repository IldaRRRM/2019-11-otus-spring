package ru.otus.homework.springlibrary.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Book {

    private Long id;
    private String name;
    private Integer releaseYear;
    private List<Author> authors;
    private List<Genre> genres;

    public Book(long id, String name, int releaseYear) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
    }

    public Book(String name, int releaseYear) {
        this.name = name;
        this.releaseYear = releaseYear;
    }

    public Book(String name, int releaseYear, List<Author> authors, List<Genre> genres) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.authors = authors;
        this.genres = genres;
    }
}
