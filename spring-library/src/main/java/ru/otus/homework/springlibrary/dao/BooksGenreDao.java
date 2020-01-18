package ru.otus.homework.springlibrary.dao;

public interface BooksGenreDao {

    void insert(Long bookId, Long genreId);

    void delete(Long bookId, Long genreId);

    void update(Long oldBookId, Long oldGenreId, Long newBookId, Long newGenreId);
}
