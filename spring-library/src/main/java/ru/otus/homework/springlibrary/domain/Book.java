package ru.otus.homework.springlibrary.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    private String name;

    private Integer releaseYear;

    private List<Author> authors;

    private List<Genre> genres;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}


