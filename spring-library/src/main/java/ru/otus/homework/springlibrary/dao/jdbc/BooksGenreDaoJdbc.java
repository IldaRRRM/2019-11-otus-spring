package ru.otus.homework.springlibrary.dao.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.otus.homework.springlibrary.dao.BooksGenreDao;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BooksGenreDaoJdbc implements BooksGenreDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public void insert(Long bookId, Long genreId) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        params.put("genreId", genreId);
        jdbc.update("INSERT INTO books_genres (book_id, genre_id) VALUES (:bookId, :genreId)", params);
    }

    @Override
    public void delete(Long bookId, Long genreId) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        params.put("genreId", genreId);
        jdbc.update("DELETE FROM books_genres WHERE book_id = :bookId and genre_id = :genreId", params);
    }

    @Override
    public void update(Long oldBookId, Long oldGenreId, Long newBookId, Long newGenreId) {
        Map<String, Object> params = new HashMap<>();

        params.put("oldBookId", oldBookId);
        params.put("oldGenreId", oldGenreId);

        params.put("newBookId", newBookId);
        params.put("newGenreId", newGenreId);

        jdbc.update("UPDATE books_genres SET (books_genres.book_id, books_genres.genre_id) = (:newBookId, :newGenreId)" +
                " WHERE books_genres.book_id = :oldBookId AND books_genres.genre_id = :oldGenreId", params);
    }
}
