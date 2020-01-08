package ru.otus.homework.springlibrary.dao.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.otus.homework.springlibrary.dao.BookDao;
import ru.otus.homework.springlibrary.domain.Book;
import ru.otus.homework.springlibrary.mapper.BookMapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long count() {
        return jdbc.queryForObject("select count(*) from books", new HashMap<>(), Long.class);
    }

    @Override
    public Long insert(Book book) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", book.getId());
        paramMap.put("name", book.getName());
        paramMap.put("releaseYear", book.getReleaseYear());
        if (book.getId() == null) {
            return jdbc.queryForObject("INSERT INTO books (name, release_year) VALUES (:name, :releaseYear) RETURNING id", paramMap, Long.class);
        } else {
            return jdbc.queryForObject("INSERT INTO books (id, name, release_year) VALUES (:id, :name, :releaseYear) RETURNING id", paramMap, Long.class);
        }
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("SELECT * FROM books", new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        jdbc.update("DELETE FROM books WHERE id = :id", paramMap);
    }

    @Override
    public void update(Book book) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", book.getId());
        paramMap.put("name", book.getName());
        paramMap.put("releaseYear", book.getReleaseYear());
        jdbc.update("UPDATE books SET (name, release_year) = (:name, :releaseYear) WHERE books.id = :id", paramMap);
    }

    @Override
    public Book getBookById(long bookId) {
        Map<String, Object> idMap = Collections.singletonMap("id", bookId);
        return jdbc.queryForObject("SELECT * FROM books WHERE id = :id", idMap, new BookMapper());
    }
}
