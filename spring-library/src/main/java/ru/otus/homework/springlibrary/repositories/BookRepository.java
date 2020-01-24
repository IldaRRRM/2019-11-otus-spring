package ru.otus.homework.springlibrary.repositories;

import ru.otus.homework.springlibrary.domain.Book;
import ru.otus.homework.springlibrary.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Long count();

    List<Book> getAllBooks();

    void deleteBookById(Long bookId);

    Optional<Book> getBookById(Long bookId);

    Book save(Book book);

    void updateBookById(Long bookId, String authorName, Integer releaseYear, String bookName, String genreName);

    void addCommentToBook(Long bookId, Comment comment);
}
