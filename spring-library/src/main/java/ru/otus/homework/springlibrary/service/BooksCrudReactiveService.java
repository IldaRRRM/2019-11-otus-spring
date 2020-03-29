package ru.otus.homework.springlibrary.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.springlibrary.domain.Book;
import ru.otus.homework.springlibrary.domain.Comment;
import ru.otus.homework.springlibrary.dto.BookDto;

public interface BooksCrudReactiveService {

    Mono<Long> getBooksCount();

    Mono<Book> addNewBook(BookDto bookDto);

    Flux<Book> getAllBooks();

    Mono<Void> deleteBookById(String bookId);

    Mono<Book> updateBook(BookDto bookDto);

    Mono<Book> getBookById(String bookId);

    Mono<Void> addCommentToBook(String bookId, String comment);

    Flux<Comment> showComments(String bookId);

}
