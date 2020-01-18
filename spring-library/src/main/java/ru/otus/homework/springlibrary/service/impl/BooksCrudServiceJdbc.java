package ru.otus.homework.springlibrary.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.homework.springlibrary.dao.AuthorDao;
import ru.otus.homework.springlibrary.dao.BookDao;
import ru.otus.homework.springlibrary.dao.BooksAuthorsDao;
import ru.otus.homework.springlibrary.dao.BooksGenreDao;
import ru.otus.homework.springlibrary.dao.GenreDao;
import ru.otus.homework.springlibrary.domain.Author;
import ru.otus.homework.springlibrary.domain.Book;
import ru.otus.homework.springlibrary.domain.Genre;
import ru.otus.homework.springlibrary.service.BooksCrudService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BooksCrudServiceJdbc implements BooksCrudService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final BooksAuthorsDao booksAuthorsDao;
    private final BooksGenreDao booksGenreDao;

    @Override
    public Long getBooksCount() {
        log.info("Запрос на получение количества всех книг");
        return bookDao.count();
    }

    @Override
    public Long addNewBook(String name, Integer releaseYear, String[] authors, String[] genre) {
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

    @Override
    public List<Book> getAllBooks() {
        log.info("Выполняется запрос на получение всех книг");
        List<Book> bookList = bookDao.getAll();
        log.debug("Запрос на получение всех книг выполнен успешно");
        return bookList;
    }

    @Override
    public void deleteBookById(Long bookId) {
        log.info("Удаление книги по id = {}", bookId);
        bookDao.deleteById(bookId);
        authorDao.getAuthorsByBookId(bookId).forEach(author -> booksAuthorsDao.delete(bookId, author.getId()));
        genreDao.getGenresByBookId(bookId).forEach(genre -> booksGenreDao.delete(bookId, genre.getId()));
        log.debug("Удаление книги с id = {} успешно завершено", bookId);
    }

    @Override
    public void updateBook(Long id, String authorName, String bookName, String genreName, Integer releaseYear) {
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

    @Override
    public Book getBookById(Long bookId) {
        return bookDao.getBookById(bookId);
    }
}
