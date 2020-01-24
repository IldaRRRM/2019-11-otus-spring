package ru.otus.homework.springlibrary.repositories.impl;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.springlibrary.domain.Author;
import ru.otus.homework.springlibrary.domain.Book;
import ru.otus.homework.springlibrary.domain.Comment;
import ru.otus.homework.springlibrary.domain.Genre;
import ru.otus.homework.springlibrary.repositories.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long count() {
        return (long) entityManager.createQuery("SELECT COUNT(id) FROM Book").getResultList().get(0);
    }

    @Transactional
    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            entityManager.persist(book);
            return book;
        } else {
            return entityManager.merge(book);
        }
    }

    @Transactional
    @Override
    public void updateBookById(Long bookId, String authorName, Integer releaseYear, String bookName, String genreName) {
        Book book = entityManager.find(Book.class, bookId);
        if (authorName != null) {
            addAuthorToBook(book, authorName);
        }
        if (genreName != null) {
            addGenreToBook(book, genreName);
        }

        if (releaseYear != 0) {
            book.setReleaseYear(releaseYear);
            entityManager.merge(book);
        }
        if (bookName != null) {
            book.setName(bookName);
            entityManager.merge(book);
        }
    }

    @Transactional
    @Override
    public List<Book> getAllBooks() {
        TypedQuery<Book> select_b_from_book_b = entityManager.createQuery("SELECT b FROM Book b", Book.class);
        List<Book> resultList = select_b_from_book_b.getResultList();
        resultList.forEach(book -> {
            Hibernate.initialize(book.getAuthors());
            Hibernate.initialize(book.getGenres());
        });
        return resultList;
    }

    @Transactional
    @Override
    public void deleteBookById(Long bookId) {
        Query deleteQuery = entityManager.createQuery("DELETE FROM Book b WHERE b.id = :id");
        deleteQuery.setParameter("id", bookId);
        deleteQuery.executeUpdate();
    }

    @Transactional
    @Override
    public Optional<Book> getBookById(Long bookId) {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.id = :bookId", Book.class);
        query.setParameter("bookId", bookId);
        Book bookById = query.getSingleResult();
        if (bookById != null) {
            if (bookById.getAuthors() != null) {
                Hibernate.initialize(bookById.getAuthors());
            }
            if (bookById.getGenres() != null) {
                Hibernate.initialize(bookById.getGenres());
            }
            if (bookById.getComments() != null) {
                Hibernate.initialize(bookById.getComments());
            }
        }
        return Optional.ofNullable(bookById);
    }

    @Transactional
    @Override
    public void addCommentToBook(Long bookId, Comment comment) {
        Book book = entityManager.find(Book.class, bookId);
        if (book.getComments() == null) {
            List<Comment> bookComments = new ArrayList<>();
            bookComments.add(comment);
            book.setComments(bookComments);
        } else {
            book.getComments().add(comment);
        }
        entityManager.merge(book);
    }

    private void addAuthorToBook(Book book, String authorName) {
        List<Author> authors = book.getAuthors();
        if (authors == null) {
            List<Author> createdAuthor = new ArrayList<>();
            createdAuthor.add(new Author(authorName));
            book.setAuthors(createdAuthor);
        } else {
            if (authors.stream().noneMatch(author -> author.getName().equals(authorName))) {
                book.getAuthors().add(new Author(authorName));
            }
        }
        entityManager.merge(book);
    }

    private void addGenreToBook(Book book, String genreName) {
        List<Genre> genresOfBook = book.getGenres();
        if (genresOfBook == null) {
            List<Genre> genres = new ArrayList<>();
            genres.add(new Genre(genreName));
            book.setGenres(genres);
        } else {
            if (genresOfBook.stream().noneMatch(genre -> genre.getName().equals(genreName))) {
                book.getGenres().add(new Genre(genreName));
            }
        }
        entityManager.merge(book);
    }
}
