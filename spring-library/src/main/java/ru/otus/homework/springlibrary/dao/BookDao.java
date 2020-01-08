package ru.otus.homework.springlibrary.dao;

import ru.otus.homework.springlibrary.domain.Book;

import java.util.List;

public interface BookDao {

    long count();

    Long insert(Book book);

    List<Book> getAll();

    void deleteById(long id);

    void update(Book book);

    Book getBookById(long bookId);
}
