package ru.otus.homework.springlibrary.dao.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.springlibrary.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GenreDaoJdbcTest {

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    private static final Long TESTING_GENRE_ID = -10L;
    private static final Long INITIAL_BOOK_ID = -1L;

    @Test
    @DisplayName("Должен вернуть ожидаемое количество жанров")
    void count() {
        int expected = genreDaoJdbc.getAll().size();
        assertThat(genreDaoJdbc.count()).isEqualTo(expected);
    }

    @Test
    @DisplayName("Должен добавить запись в базу данных и вернуть Id добавленной записи")
    void shouldInsertToBdAndReturnedId() {
        Genre expectedGenre = new Genre(TESTING_GENRE_ID, "комедия");
        assertThat(genreDaoJdbc.insert(expectedGenre)).isEqualTo(TESTING_GENRE_ID);
        assertThat(genreDaoJdbc.findGenreById(TESTING_GENRE_ID)).isEqualTo(expectedGenre);
        genreDaoJdbc.deleteById(TESTING_GENRE_ID);
    }

    @Test
    @DisplayName("Добавление и удаление записи. Проверка происходит по размеру списка общих записей")
    void shouldReturnCorrectSizeOfRecordsAfterDelete() {
        genreDaoJdbc.insert(new Genre(TESTING_GENRE_ID, "комедия"));
        int sizeBeforeDelete = genreDaoJdbc.getAll().size();
        genreDaoJdbc.deleteById(TESTING_GENRE_ID);
        assertThat(sizeBeforeDelete - 1).isEqualTo(genreDaoJdbc.getAll().size());
    }

    @Test
    @DisplayName("Обновление жанра по id")
    void update() {
        Genre expectedGenre = new Genre(TESTING_GENRE_ID, "комедия");
        genreDaoJdbc.insert(expectedGenre);
        expectedGenre.setName("трагедия");
        genreDaoJdbc.update(expectedGenre);
        assertThat(genreDaoJdbc.findGenreById(TESTING_GENRE_ID)).isEqualTo(expectedGenre);
        genreDaoJdbc.deleteById(TESTING_GENRE_ID);
    }

    @Test
    @DisplayName("Поиск всех жанров по id книги (Id книги берется из иниц. тестового скрипта)")
    void getGenresByBookId() {
        assertThat(genreDaoJdbc.getGenresByBookId(INITIAL_BOOK_ID).size()).isEqualTo(2);
    }
}