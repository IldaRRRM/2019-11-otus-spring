package ru.otus.homework.springlibrary.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.springlibrary.domain.Author;
import ru.otus.homework.springlibrary.domain.Book;
import ru.otus.homework.springlibrary.domain.Comment;
import ru.otus.homework.springlibrary.domain.Genre;
import ru.otus.homework.springlibrary.repositories.AuthorRepository;
import ru.otus.homework.springlibrary.repositories.BookRepository;
import ru.otus.homework.springlibrary.repositories.GenreRepository;
import ru.otus.homework.springlibrary.service.BooksCrudReactiveService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BooksCrudServiceJpa implements BooksCrudReactiveService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public Mono<Book> addNewBook(String name, Integer releaseYear, String[] authors, String[] genre) {
        List<Author> booksAuthors = addAuthorsToRep(Arrays.stream(authors).map(Author::new).collect(Collectors.toList()));
        List<Genre> booksGenres = addGenreToRep(Arrays.stream(genre).map(Genre::new).collect(Collectors.toList()));
        return bookRepository.save(new Book(name, releaseYear, booksAuthors, booksGenres));
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
    public Mono<Void> updateBook(String id, String authorName, String bookName, String genreName, Integer releaseYear) {
        Book book = bookRepository.findById(id).block();
        if (book != null) {
            updateAuthorName(book, authorName)
                    .updateBookName(book, bookName)
                    .updateGenre(book, genreName)
                    .updateReleaseYear(book, releaseYear);
            bookRepository.save(book);
        }
        return Mono.empty();
    }

    @Override
    public Mono<Book> getBookById(String bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public Mono<Void> addCommentToBook(String bookId, String comment) {
        bookRepository.findById(bookId);
//        if (book.getComments() != null) {
//            book.getComments().add(new Comment(comment));
//        } else {
//            List<Comment> comments = new ArrayList<>();
//            comments.add(new Comment(comment));
//            book.setComments(comments);
//        }
//        bookRepository.save(book);
        return Mono.empty();
    }

    @Override
    public Flux<Comment> showComments(String bookId) {
//        Book book = bookRepository.findById(bookId).block();
//        if (book != null) {
//            return book.getComments() != null ? Flux.create(book.getComments()) : Flux.empty();
//        }
        return Flux.empty();
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
            Optional<Author> authorByNameAndCountry = authorRepository.findAuthorByNameIgnoreCase(currentAuthor.getName());
//            Author addedAuthor = authorByNameAndCountry.orElseGet(() -> authorRepository.save(currentAuthor));
//            authorsWithId.add(addedAuthor);
        }
        return authorsWithId;
    }

    private List<Genre> addGenreToRep(List<Genre> genres) {
        List<Genre> genresWithId = new ArrayList<>();
        for (Genre genre : genres) {
            Optional<Genre> genreByName = genreRepository.findGenreByNameIgnoreCase(genre.getName());
//            Genre addedAuthor = genreByName.orElseGet(() -> genreRepository.save(genre));
//            genresWithId.add(addedAuthor);
        }
        return genresWithId;
    }
}
