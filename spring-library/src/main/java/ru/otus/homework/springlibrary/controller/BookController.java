package ru.otus.homework.springlibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.springlibrary.domain.Book;
import ru.otus.homework.springlibrary.domain.Comment;
import ru.otus.homework.springlibrary.dto.BookDto;
import ru.otus.homework.springlibrary.dto.CommentDto;
import ru.otus.homework.springlibrary.service.BooksCrudReactiveService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    @GetMapping
    public Flux<Book> getAllBooks() {
        return booksCrudService.getAllBooks();
    }

    private final BooksCrudReactiveService booksCrudService;

    @GetMapping("/count")
    public Mono<Long> getBooksCount() {
        return booksCrudService.getBooksCount();
    }

    @GetMapping("/{id}")
    public Mono<Book> getBookById(@PathVariable String id) {
        return booksCrudService.getBookById(id);
    }

    @PostMapping("/create")
    public Mono<Book> createBook(@RequestBody BookDto bookDto) {
        return booksCrudService.addNewBook(bookDto);
    }

    @PostMapping("/update")
    public Mono<Book> updateBook(@RequestBody BookDto bookDto) {
        return booksCrudService.updateBook(bookDto);
    }

    @PostMapping("/delete/{id}")
    public Mono<Void> deleteBook(@PathVariable String id) {
        return booksCrudService.deleteBookById(id);
    }

    @GetMapping("/{id}/comments")
    public Flux<Comment> showBooksComments(@PathVariable String id) {
        return booksCrudService.showComments(id);
    }

    @PostMapping("/{id}/comments/add")
    public Mono<Void> addCommentToBook(@PathVariable String id, @RequestBody CommentDto commentDto) {
        return booksCrudService.addCommentToBook(id, commentDto);
    }

}
