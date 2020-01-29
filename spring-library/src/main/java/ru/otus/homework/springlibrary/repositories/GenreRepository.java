package ru.otus.homework.springlibrary.repositories;

import ru.otus.homework.springlibrary.domain.Genre;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GenreRepository {

    Long count();

    List<Genre> getAllGenres();

    void deleteGenreById(Long genreId);

    Optional<Genre> getGenreById(Long genreId);

    Genre save(Genre genre);

    void updateGenreById(Long id, Map<String, String> updatedFields);
}
