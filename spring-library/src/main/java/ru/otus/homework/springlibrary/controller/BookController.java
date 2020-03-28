package ru.otus.homework.springlibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.springlibrary.domain.Book;
import ru.otus.homework.springlibrary.repositories.BookRepository;

@RestController("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("/count")
    public Mono<Long> getBooksCount() {
        return bookRepository.count();
    }

    @GetMapping("/{id}")
    public Mono<Book> getBookById(@PathVariable String id) {
        return bookRepository.findById(id);
    }

    @GetMapping
    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

}
