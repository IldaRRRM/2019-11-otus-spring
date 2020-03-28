package ru.otus.homework.springlibrary.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.springlibrary.domain.Book;
import ru.otus.homework.springlibrary.domain.Comment;

public interface BooksCrudReactiveService {

    Mono<Long> getBooksCount();

    Mono<Book> addNewBook(String name, Integer releaseYear, String[] authors, String[] genre);

    Flux<Book> getAllBooks();

    Mono<Void> deleteBookById(String bookId);

    Mono<Void> updateBook(String id, String authorName, String bookName, String genreName, Integer releaseYear);

    Mono<Book> getBookById(String bookId);

    Mono<Void> addCommentToBook(String bookId, String comment);

    Flux<Comment> showComments(String bookId);


}
