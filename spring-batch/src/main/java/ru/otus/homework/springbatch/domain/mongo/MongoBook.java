package ru.otus.homework.springbatch.domain.mongo;

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
public class MongoBook {
    @Id
    private String id;
    @Setter
    private String name;
    @Setter
    private Integer releaseYear;
    @Setter
    private List<MongoAuthor> authors;
    @Setter
    private List<MongoGenre> genres;
    @Setter
    private List<MongoComment> comments;

    public MongoBook(String id, String name, int releaseYear) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
    }

    public MongoBook(String name, int releaseYear, List<MongoAuthor> authors, List<MongoGenre> genres) {
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
