package ru.otus.homework.springlibrary.dao;

public interface BooksAuthorsDao {

    void insert(Long bookId, Long authorId);

    void delete(Long bookId, Long authorId);

    void update(Long oldBookId, Long oldAuthorId, Long newBookId, Long newAuthorId);
}
