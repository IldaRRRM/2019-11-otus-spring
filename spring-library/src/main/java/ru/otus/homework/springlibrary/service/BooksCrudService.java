package ru.otus.homework.springlibrary.service;

import ru.otus.homework.springlibrary.domain.Book;

import java.util.List;

public interface BooksCrudService {

    Long getBooksCount();

    Long addNewBook(String name, Integer releaseYear, String[] authors, String[] genre);

    List<Book> getAllBooks();

    void deleteBookById(Long bookId);

    void updateBook(Long id, String authorName, String bookName, String genreName, Integer releaseYear);

    Book getBookById(Long bookId);
}

