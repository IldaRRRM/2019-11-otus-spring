package ru.otus.homework.springlibrary.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.springlibrary.domain.Author;
import ru.otus.homework.springlibrary.domain.Book;
import ru.otus.homework.springlibrary.domain.Comment;
import ru.otus.homework.springlibrary.domain.Genre;
import ru.otus.homework.springlibrary.repositories.BookRepository;
import ru.otus.homework.springlibrary.service.BooksCrudService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BooksCrudServiceJpql implements BooksCrudService {

    private final BookRepository bookRepository;

    @Override
    public Long getBooksCount() {
        return bookRepository.count();
    }

    @Transactional
    @Override
    public Long addNewBook(String name, Integer releaseYear, String[] authors, String[] genre) {
        List<Author> booksAuthors = Arrays.stream(authors).map(Author::new).collect(Collectors.toList());
        List<Genre> booksGenres = Arrays.stream(genre).map(Genre::new).collect(Collectors.toList());
        Book savedBook = bookRepository.save(new Book(name, releaseYear, booksAuthors, booksGenres));
        return savedBook.getId();
    }

    @Transactional
    @Override
    public List<Book> getAllBooks() {
        log.info("Выполнение запроса на получение всех книг");
        return bookRepository.getAllBooks();
    }

    @Transactional
    @Override
    public void deleteBookById(Long bookId) {
        bookRepository.deleteBookById(bookId);
    }

    @Transactional
    @Override
    public void updateBook(Long id, String authorName, String bookName, String genreName, Integer releaseYear) {
        bookRepository.updateBookById(id, authorName, releaseYear, bookName, genreName);
    }

    @Override
    public Book getBookById(Long bookId) {
        return bookRepository.getBookById(bookId).orElseThrow(RuntimeException::new);
    }

    @Override
    public void addCommentToBook(Long bookId, String comment) {
        bookRepository.addCommentToBook(bookId, new Comment(comment));
    }

    @Override
    public List<Comment> showComments(Long bookId) {
        return bookRepository.getBookById(bookId).orElseThrow().getComments();
    }
}
