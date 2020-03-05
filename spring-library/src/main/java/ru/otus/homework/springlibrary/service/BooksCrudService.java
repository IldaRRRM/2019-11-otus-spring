package ru.otus.homework.springlibrary.service;

import ru.otus.homework.springlibrary.domain.Book;
import ru.otus.homework.springlibrary.domain.Comment;

import java.util.List;

public interface BooksCrudService {

    Long getBooksCount();

    String addNewBook(String name, Integer releaseYear, String[] authors, String[] genre);

    List<Book> getAllBooks();

    void deleteBookById(String bookId);

    void updateBook(String id, String authorName, String bookName, String genreName, Integer releaseYear);

    Book getBookById(String bookId);

    void addCommentToBook(String bookId, String comment);

    List<Comment> showComments(String bookId);
}

