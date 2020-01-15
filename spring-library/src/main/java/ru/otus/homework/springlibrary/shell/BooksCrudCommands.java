package ru.otus.homework.springlibrary.shell;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.springlibrary.domain.Book;
import ru.otus.homework.springlibrary.service.BooksCrudService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
@Slf4j
public class BooksCrudCommands {

    private final BooksCrudService booksCrudService;

    @ShellMethod(value = "get count of all books", key = {"books_count", "ct"})
    public Long getBooksCount() {
        return booksCrudService.getBooksCount();
    }

    @ShellMethod(value = "add a new Book", key = {"add a new Book", "insb"})
    @Transactional
    public Long addNewBook(@ShellOption String name, @ShellOption Integer releaseYear, @ShellOption String[] authors, @ShellOption String[] genre) {
        return booksCrudService.addNewBook(name, releaseYear, authors, genre);
    }

    @ShellMethod(value = "get all books", key = {"get all books", "gab"})
    public List<Book> getAllBooks() {
        return booksCrudService.getAllBooks();
    }

    @Transactional
    @ShellMethod(value = "delete book by id", key = {"delete book by id", "did"})
    public void deleteBookById(@ShellOption Long bookId) {
        booksCrudService.deleteBookById(bookId);
    }

    @Transactional
    @ShellMethod(value = "update book", key = {"updbook, upb"})
    public void updateBook(@ShellOption("--id") Long id,
                           @ShellOption("--ar") String authorName,
                           @ShellOption("--bn") String bookName,
                           @ShellOption("--gr") String genreName,
                           @ShellOption("--ry") Integer releaseYear) {
        booksCrudService.updateBook(id, authorName, bookName, genreName, releaseYear);
    }

    @ShellMethod(value = "get book by Id", key = {"get book by id", "gtb"})
    public Book getBookById(@ShellOption Long bookId) {
        return booksCrudService.getBookById(bookId);
    }
}
