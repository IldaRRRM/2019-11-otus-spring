package ru.otus.homework.springlibrary.shell;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.springlibrary.dao.AuthorDao;
import ru.otus.homework.springlibrary.dao.BookDao;
import ru.otus.homework.springlibrary.dao.BooksAuthorsDao;
import ru.otus.homework.springlibrary.dao.BooksGenreDao;
import ru.otus.homework.springlibrary.dao.GenreDao;
import ru.otus.homework.springlibrary.domain.Author;
import ru.otus.homework.springlibrary.domain.Book;
import ru.otus.homework.springlibrary.domain.Genre;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
@Slf4j
public class BooksCrudCommands {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final BooksAuthorsDao booksAuthorsDao;
    private final BooksGenreDao booksGenreDao;

    @ShellMethod(value = "get count of all books", key = {"books_count", "ct"})
    public Long getBooksCount() {
        log.info("Запрос на получение количества всех книг");
        return bookDao.count();
    }

    @ShellMethod(value = "add a new Book", key = {"add a new Book", "insb"})
    @Transactional
    public Long addNewBook(@ShellOption String name, @ShellOption Integer releaseYear, @ShellOption String[] authors, @ShellOption String[] genre) {
        log.info("Добавление новой книги");

        List<Author> authorList = Arrays.stream(authors).map(Author::new).collect(Collectors.toList());
        List<Long> authorsId = authorList.stream().map(authorDao::insert).collect(Collectors.toList());

        List<Genre> genreList = Arrays.stream(genre).map(Genre::new).collect(Collectors.toList());
        List<Long> genreId = genreList.stream().map(genreDao::insert).collect(Collectors.toList());

        Book book = new Book(name, releaseYear, authorList, genreList);
        Long bookId = bookDao.insert(book);

        authorsId.forEach(currentAuthorId -> booksAuthorsDao.insert(bookId, currentAuthorId));
        genreId.forEach(currentGenreId -> booksGenreDao.insert(bookId, currentGenreId));

        log.info("Книга {} добавлена Id = {}", book, bookId);

        return bookId;
    }

    @ShellMethod(value = "get all books", key = {"get all books", "gab"})
    public List<Book> getAllBooks() {
        log.info("Выполняется запрос на получение всех книг");
        List<Book> bookList = bookDao.getAll();
        bookList.forEach(book -> {
            book.setAuthors(authorDao.getAuthorsByBookId(book.getId()));
            book.setGenres(genreDao.getGenresByBookId(book.getId()));
        });
        log.debug("Запрос на получение всех книг выполнен успешно");
        return bookList;
    }

    @Transactional
    @ShellMethod(value = "delete book by id", key = {"delete book by id", "did"})
    public void deleteBookById(@ShellOption Long bookId) {
        log.info("Удаление книги по id = {}", bookId);
        bookDao.deleteById(bookId);
        authorDao.getAuthorsByBookId(bookId).forEach(author -> booksAuthorsDao.delete(bookId, author.getId()));
        genreDao.getGenresByBookId(bookId).forEach(genre -> booksGenreDao.delete(bookId, genre.getId()));
        log.debug("Удаление книги с id = {} успешно завершено", bookId);
    }

    @Transactional
    @ShellMethod(value = "update book", key = {"updbook, upb"})
    public void updateBook(@ShellOption("--id") Long id,
                           @ShellOption("--ar") String authorName,
                           @ShellOption("--bn") String bookName,
                           @ShellOption("--gr") String genreName,
                           @ShellOption("--ry") Integer releaseYear) {
        //TODO Допилить в jpa
        if (bookName != null) {
            bookDao.update(new Book(id, bookName, releaseYear));
        }
        if (authorName != null) {
            List<Author> authorsByBookId = authorDao.getAuthorsByBookId(id);
            if (authorsByBookId.stream().noneMatch(author -> author.getName().equals(authorName))) {
                Long authorId = authorDao.insert(new Author(authorName));
                booksAuthorsDao.insert(id, authorId);
            }
        }
        if (genreName != null) {
            if (genreDao.getGenresByBookId(id).stream().noneMatch(genre -> genre.getName().equals(genreName))) {
                Long newGenreId = genreDao.insert(new Genre(genreName));
                booksGenreDao.insert(id, newGenreId);
            }
        }
    }

    @ShellMethod(value = "get book by Id", key = {"get book by id", "gtb"})
    public Book getBookById(@ShellOption Long bookId) {
        Book searchedBook = bookDao.getBookById(bookId);
        searchedBook.setGenres(genreDao.getGenresByBookId(bookId));
        searchedBook.setAuthors(authorDao.getAuthorsByBookId(bookId));
        return searchedBook;
    }
}
