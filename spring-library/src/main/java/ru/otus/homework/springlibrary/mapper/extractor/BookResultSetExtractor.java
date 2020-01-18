package ru.otus.homework.springlibrary.mapper.extractor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.otus.homework.springlibrary.domain.Author;
import ru.otus.homework.springlibrary.domain.Book;
import ru.otus.homework.springlibrary.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class BookResultSetExtractor implements ResultSetExtractor<List<Book>> {

    @Override
    public List<Book> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            long bookId = resultSet.getLong("bid");
            if (books.stream().noneMatch(book -> book.getId().equals(bookId))) {
                List<Author> authorsOfBook = new ArrayList<>();
                List<Genre> bookGenres = new ArrayList<>();
                String name = resultSet.getString("bname");
                int release_year = resultSet.getInt("release_year");
                fillInfoAboutAuthor(new Author(), resultSet).ifPresentOrElse(authorsOfBook::add, () -> log.debug("Нет автора"));
                fillInfoAboutGenre(new Genre(), resultSet).ifPresentOrElse(bookGenres::add, () -> log.debug("Жанра нет"));
                books.add(new Book(bookId, name, release_year, authorsOfBook, bookGenres));
            } else {
                Book existsBook = books.stream().filter(book -> book.getId().equals(bookId)).findAny().orElseThrow();

                Author author = fillInfoAboutAuthor(new Author(), resultSet).orElseThrow(null);
                if (author != null && !existsBook.getAuthors().contains(author)) {
                    existsBook.getAuthors().add(author);
                }

                Genre genre = fillInfoAboutGenre(new Genre(), resultSet).orElseThrow(null);
                if (genre != null && !existsBook.getGenres().contains(genre)) {
                    existsBook.getGenres().add(genre);
                }
            }
        }
        return books;
    }

    private boolean checkNotNullField(ResultSet resultSet, String filed, Class type) throws SQLException {
        return resultSet.getObject(filed, type) != null;
    }

    private Optional<Author> fillInfoAboutAuthor(Author author, ResultSet resultSet) throws SQLException {
        long authorId = resultSet.getLong("aid");
        if (authorId != 0) {
            author.setId(authorId);
            if (checkNotNullField(resultSet, "aname", String.class)) {
                author.setName(resultSet.getString("aname"));
            }
            if (checkNotNullField(resultSet, "country", String.class)) {
                author.setCountry(resultSet.getString("country"));
            }
            return Optional.of(author);
        }
        return Optional.empty();
    }

    private Optional<Genre> fillInfoAboutGenre(Genre genre, ResultSet resultSet) throws SQLException {
        long genreId = resultSet.getLong("gid");
        if (genreId != 0) {
            genre.setId(genreId);
            if (checkNotNullField(resultSet, "gname", String.class)) {
                genre.setName(resultSet.getString("gname"));
            }
            return Optional.of(genre);
        }
        return Optional.empty();
    }
}
