package ru.otus.homework.springlibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    @Id
    private String id;
    private String name;
    private Integer releaseYear;
    private List<AuthorDto> authors;
    private List<GenreDto> genres;
    private List<CommentDto> comments;
}
