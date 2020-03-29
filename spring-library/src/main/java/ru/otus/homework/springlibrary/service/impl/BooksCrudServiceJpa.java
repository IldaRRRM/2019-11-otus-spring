package ru.otus.homework.springlibrary.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.springlibrary.domain.Author;
import ru.otus.homework.springlibrary.domain.Book;
import ru.otus.homework.springlibrary.domain.Comment;
import ru.otus.homework.springlibrary.domain.Genre;
import ru.otus.homework.springlibrary.dto.BookDto;
import ru.otus.homework.springlibrary.repositories.BookRepository;
import ru.otus.homework.springlibrary.service.BooksCrudReactiveService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BooksCrudServiceJpa implements BooksCrudReactiveService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public Mono<Book> addNewBook(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        return bookRepository.save(book);
    }

    @Override
    public Mono<Long> getBooksCount() {
        return bookRepository.count();
    }

    @Override
    public Flux<Book> getAllBooks() {
        log.info("Выполнение запроса на получение всех книг");
        return bookRepository.findAll();
    }

    @Override
    public Mono<Void> deleteBookById(String bookId) {
        return bookRepository.deleteById(bookId);
    }

    @Override
    public Mono<Book> updateBook(BookDto bookDto) {
        return bookRepository.findById(bookDto.getId())
                .flatMap(book -> {
                    updateBookFields(bookDto, book);
                    return bookRepository.save(book);
                });
    }

    @Override
    public Mono<Book> getBookById(String bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public Mono<Void> addCommentToBook(String bookId, String comment) {
        return bookRepository.findById(bookId).flatMap(book -> {
            book.getComments().add(new Comment(comment));
            return bookRepository.save(book);
        }).thenEmpty(Mono.empty());
    }

    @Override
    public Flux<Comment> showComments(String bookId) {
        return bookRepository.findById(bookId).map(Book::getComments).flatMapMany(Flux::fromIterable);
    }

    private void updateBookFields(BookDto bookDto, Book book) {
        if (bookDto.getName() != null) {
            book.setName(bookDto.getName());
        }
        if (bookDto.getGenres() != null) {
            List<Genre> updatedGenres = bookDto.getGenres().stream().map(genreDto -> modelMapper.map(genreDto, Genre.class)).collect(Collectors.toList());
            book.getGenres().addAll(updatedGenres);
        }
        if (bookDto.getAuthors() != null) {
            List<Author> authorList = bookDto.getAuthors().stream().map(authorDto -> modelMapper.map(authorDto, Author.class)).collect(Collectors.toList());
            book.getAuthors().addAll(authorList);
        }
        if (bookDto.getComments() != null) {
            List<Comment> comments = book.getComments().stream().map(comment -> modelMapper.map(comment, Comment.class)).collect(Collectors.toList());
            book.getComments().addAll(comments);
        }

        if (bookDto.getReleaseYear() != null) {
            book.setReleaseYear(bookDto.getReleaseYear());
        }
    }
}
