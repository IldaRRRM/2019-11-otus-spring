package ru.otus.homework.springlibrary.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Document(collection = "books")
public class Book {

    @Id
    private String id;
    @Setter
    private String name;
    @Setter
    private Integer releaseYear;
    @Setter
    private List<Author> authors;
    @Setter
    private List<Genre> genres;
    @Setter
    private List<Comment> comments;

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


