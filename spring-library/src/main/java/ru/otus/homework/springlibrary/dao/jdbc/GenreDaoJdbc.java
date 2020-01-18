package ru.otus.homework.springlibrary.dao.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.otus.homework.springlibrary.dao.GenreDao;
import ru.otus.homework.springlibrary.domain.Genre;
import ru.otus.homework.springlibrary.mapper.GenreMapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from genres", Long.class);
    }

    @Override
    public Long insert(Genre genre) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", genre.getId());
        paramMap.put("name", genre.getName());
        if (genre.getId() == null) {
            return jdbc.queryForObject("INSERT INTO genres (name) VALUES (:name) " +
                    "ON CONFLICT ON CONSTRAINT genres_name_key DO UPDATE SET name=EXCLUDED.name " +
                    "RETURNING id", paramMap, Long.class);
        } else {
            return jdbc.queryForObject("INSERT INTO genres (id, name) VALUES (:id, :name) " +
                    "ON CONFLICT ON CONSTRAINT genres_name_key DO UPDATE SET name=EXCLUDED.name " +
                    "RETURNING id", paramMap, Long.class);
        }
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.getJdbcOperations().query("SELECT * FROM genres", new GenreMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        jdbc.update("DELETE FROM genres WHERE id = :id", paramMap);
    }

    @Override
    public void update(Genre genre) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", genre.getId());
        paramMap.put("name", genre.getName());
        jdbc.update("UPDATE genres SET name = :name  WHERE genres.id = :id", paramMap);
    }

    @Override
    public List<Genre> getGenresByBookId(Long bookId) {
        Map<String, Object> params = Collections.singletonMap("id", bookId);
        return jdbc.query("SELECT g.name, g.id FROM genres AS g " +
                "INNER JOIN books_genres bg on g.id = bg.genre_id " +
                "INNER JOIN books b on bg.book_id = b.id " +
                "WHERE b.id = :id", params, new GenreMapper());
    }

    @Override
    public Genre findGenreById(Long genreId) {
        Map<String, Object> params = Collections.singletonMap("id", genreId);
        return jdbc.queryForObject("SELECT * FROM genres WHERE genres.id = :id", params, new GenreMapper());
    }
}
