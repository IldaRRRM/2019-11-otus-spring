package ru.otus.homework.springlibrary.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.springlibrary.domain.Genre;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("Проверка метода поиска жанра по его названию")
    void shouldReturnSavedBeforeGenreByName() {
        Genre expectedGenre = new Genre("July");
        genreRepository.save(expectedGenre);
        Genre actualGenre = genreRepository.findGenreByNameIgnoreCase("july").orElseThrow();
        assertThat(actualGenre).isEqualTo(expectedGenre);
        genreRepository.delete(actualGenre);
    }
}