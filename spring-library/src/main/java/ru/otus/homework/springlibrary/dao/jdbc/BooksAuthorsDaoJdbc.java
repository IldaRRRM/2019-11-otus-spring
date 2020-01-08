package ru.otus.homework.springlibrary.dao.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.otus.homework.springlibrary.dao.BooksAuthorsDao;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BooksAuthorsDaoJdbc implements BooksAuthorsDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public void insert(Long bookId, Long authorId) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        params.put("authorId", authorId);
        jdbc.update("INSERT INTO authors_books (book_id, author_id) VALUES (:bookId, :authorId)", params);
    }

    @Override
    public void delete(Long bookId, Long authorId) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        params.put("authorId", authorId);
        jdbc.update("DELETE FROM authors_books WHERE book_id = :bookId AND author_id = :authorId", params);
    }

    @Override
    public void update(Long oldBookId, Long oldAuthorId, Long newBookId, Long newAuthorId) {
        Map<String, Object> params = new HashMap<>();

        params.put("oldBookId", oldBookId);
        params.put("oldAuthorId", oldAuthorId);

        params.put("newBookId", newBookId);
        params.put("newAuthorId", newAuthorId);

        jdbc.update("UPDATE authors_books " +
                "SET (book_id, author_id) = (:newBookId, :newAuthorId) " +
                "WHERE book_id = :oldBookId AND author_id = :oldAuthorId", params);
    }
}
