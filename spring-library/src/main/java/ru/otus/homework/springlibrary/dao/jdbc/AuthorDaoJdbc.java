package ru.otus.homework.springlibrary.dao.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.otus.homework.springlibrary.dao.AuthorDao;
import ru.otus.homework.springlibrary.domain.Author;
import ru.otus.homework.springlibrary.mapper.AuthorMapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from authors", Long.class);
    }

    @Override
    public Long insert(Author author) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", author.getName());
        paramMap.put("country", author.getCountry());
        return jdbc.queryForObject("INSERT INTO authors (name, country) VALUES (:name, :country) " +
                "ON CONFLICT ON CONSTRAINT authors_name_key DO UPDATE SET name=EXCLUDED.name RETURNING id", paramMap, Long.class);
    }

    @Override
    public List<Author> getAll() {
        return jdbc.getJdbcOperations().query("SELECT * FROM authors", new AuthorMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        jdbc.update("DELETE FROM authors WHERE id = :id", paramMap);
    }

    @Override
    public void update(Author author) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", author.getId());
        paramMap.put("name", author.getName());
        paramMap.put("country", author.getCountry());
        jdbc.update("UPDATE authors SET (authors.name, authors.country)  = (:name, :country)" +
                " WHERE authors.id = :id", paramMap);
    }

    @Override
    public List<Author> getAuthorsByBookId(Long bookId) {
        Map<String, Object> paramsMap = Collections.singletonMap("id", bookId);
        return jdbc.query("SELECT a.id, a.name, a.country FROM authors as a " +
                "INNER JOIN authors_books ab on a.id = ab.author_id" +
                " INNER JOIN books b on ab.book_id = b.id" +
                " WHERE b.id = :id", paramsMap, new AuthorMapper());
    }
}
