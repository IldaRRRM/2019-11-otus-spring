package ru.otus.homework.springlibrary.dao;

import ru.otus.homework.springlibrary.domain.Genre;

import java.util.List;

public interface GenreDao {

    long count();

    Long insert(Genre genre);

    List<Genre> getAll();

    void deleteById(long id);

    void update(Genre genre);

    List<Genre> getGenresByBookId(Long bookId);

    Genre findGenreById(Long genreId);
}
