package ru.otus.homework.springlibrary.repositories.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework.springlibrary.domain.Genre;
import ru.otus.homework.springlibrary.repositories.GenreRepository;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(GenreRepositoryImpl.class)
class GenreRepositoryImplTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("Проверка методов count и getAll")
    void shouldReturnTheSameLongThatAllMethodSize() {
        assertThat(genreRepository.count()).isEqualTo(genreRepository.getAllGenres().size());
    }

    @Test
    @DisplayName("Проверка удаления жанра")
    void shouldDeleteGenreById() {
        Genre genre = genreRepository.save(new Genre("Жанрович"));
        Long countAfterAdd = genreRepository.count();
        genreRepository.deleteGenreById(genre.getId());
        assertThat(genreRepository.count()).isEqualTo(countAfterAdd - 1);
    }

    @Test
    @DisplayName("Проверка метода получения жанра по id")
    void shouldReturnGenreWithDefineId() {
        Genre expectedGenre = genreRepository.save(new Genre("Получаемый жанр"));
        Genre actualGenre = genreRepository.getGenreById(expectedGenre.getId()).orElseThrow();
        assertThat(actualGenre).isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("Проверка метода обновления сущности \"Genre\"")
    void shouldUpdateGenreById() {
        Genre expectedGenre = genreRepository.save(new Genre("Жанр до обновления"));
        Map<String, String> updatedFields = new HashMap<>();
        updatedFields.put("name", "Жанр после обновления");
        genreRepository.updateGenreById(expectedGenre.getId(), updatedFields);
        Genre afterUpdate = genreRepository.getGenreById(expectedGenre.getId()).orElseThrow();
        assertThat(afterUpdate.getName()).isEqualTo("Жанр после обновления");
    }
}