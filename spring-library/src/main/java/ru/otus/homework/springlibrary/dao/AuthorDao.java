package ru.otus.homework.springlibrary.dao;

import ru.otus.homework.springlibrary.domain.Author;

import java.util.List;

public interface AuthorDao {

    long count();

    Long insert(Author author);

    List<Author> getAll();

    void deleteById(long id);

    void update(Author author);

    List<Author> getAuthorsByBookId(Long bookId);

}
