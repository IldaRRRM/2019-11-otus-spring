package ru.otus.homework.springlibrary.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.homework.springlibrary.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        int release_year = resultSet.getInt("release_year");
        return new Book(id, name, release_year);
    }
}
