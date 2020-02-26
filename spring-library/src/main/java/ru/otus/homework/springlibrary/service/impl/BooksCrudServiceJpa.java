package ru.otus.homework.springlibrary.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.springlibrary.domain.Author;
import ru.otus.homework.springlibrary.domain.Book;
import ru.otus.homework.springlibrary.domain.Comment;
import ru.otus.homework.springlibrary.domain.Genre;
import ru.otus.homework.springlibrary.repositories.AuthorRepository;
import ru.otus.homework.springlibrary.repositories.BookRepository;
import ru.otus.homework.springlibrary.repositories.GenreRepository;
import ru.otus.homework.springlibrary.service.BooksCrudService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BooksCrudServiceJpa implements BooksCrudService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public Long getBooksCount() {
        return bookRepository.count();
    }

    @Transactional
    @Override
    public String addNewBook(String name, Integer releaseYear, String[] authors, String[] genre) {
        List<Author> booksAuthors = addAuthorsToRep(Arrays.stream(authors).map(Author::new).collect(Collectors.toList()));
        List<Genre> booksGenres = addGenreToRep(Arrays.stream(genre).map(Genre::new).collect(Collectors.toList()));
        Book savedBook = bookRepository.save(new Book(name, releaseYear, booksAuthors, booksGenres));
        return savedBook.getId();
    }

    @Transactional
    @Override
    public List<Book> getAllBooks() {
        log.info("Выполнение запроса на получение всех книг");
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteBookById(String bookId) {
        bookRepository.deleteById(bookId);
    }

    @Transactional
    @Override
    public void updateBook(String id, String authorName, String bookName, String genreName, Integer releaseYear) {
        Book book = bookRepository.findById(id).orElseThrow();

        updateAuthorName(book, authorName)
                .updateBookName(book, bookName)
                .updateGenre(book, genreName)
                .updateReleaseYear(book, releaseYear);

        bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book getBookById(String bookId) {
        return bookRepository.findById(bookId).orElseThrow(RuntimeException::new);
    }

    @Transactional
    @Override
    public void addCommentToBook(String bookId, String comment) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        if (book.getComments() != null) {
            book.getComments().add(new Comment(comment));
        } else {
            List<Comment> comments = new ArrayList<>();
            comments.add(new Comment(comment));
            book.setComments(comments);
        }
        bookRepository.save(book);
    }

    @Transactional
    @Override
    public List<Comment> showComments(String bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        return book.getComments() != null ? book.getComments() : new ArrayList<>();
    }

    private BooksCrudServiceJpa updateAuthorName(Book book, String authorName) {
        if (authorName != null) {
            if (book.getAuthors() != null) {
                List<Author> authors = book.getAuthors();
                if (authors.stream().noneMatch(author -> author.getName().equals(authorName))) {
                    authors.add(new Author(authorName));
                }
            } else {
                List<Author> authors = new ArrayList<>();
                authors.add(new Author(authorName));
                book.setAuthors(authors);
            }
        }
        return this;
    }

    private BooksCrudServiceJpa updateBookName(Book book, String bookName) {
        if (bookName != null) {
            book.setName(bookName);
        }
        return this;
    }

    private BooksCrudServiceJpa updateGenre(Book book, String genre) {
        if (genre != null) {
            if (book.getGenres() != null) {
                if (book.getGenres().stream().noneMatch(currentGenre -> currentGenre.getName().equals(genre))) {
                    book.getGenres().add(new Genre(genre));
                }
            } else {
                List<Genre> genres = new ArrayList<>();
                genres.add(new Genre(genre));
                book.setGenres(genres);
            }
        }
        return this;
    }

    private BooksCrudServiceJpa updateReleaseYear(Book book, Integer releaseYear) {
        if (releaseYear != 0) {
            book.setReleaseYear(releaseYear);
        }
        return this;
    }

    private List<Author> addAuthorsToRep(List<Author> authors) {
        List<Author> authorsWithId = new ArrayList<>();
        for (Author currentAuthor : authors) {
            Optional<Author> authorByNameAndCountry = authorRepository.findAuthorByName(currentAuthor.getName());
            Author addedAuthor = authorByNameAndCountry.orElseGet(() -> authorRepository.save(currentAuthor));
            authorsWithId.add(addedAuthor);
        }
        return authorsWithId;
    }

    private List<Genre> addGenreToRep(List<Genre> genres) {
        List<Genre> genresWithId = new ArrayList<>();
        for (Genre genre : genres) {
            Optional<Genre> genreByName = genreRepository.findGenreByName(genre.getName());
            Genre addedAuthor = genreByName.orElseGet(() -> genreRepository.save(genre));
            genresWithId.add(addedAuthor);
        }
        return genresWithId;
    }
}
