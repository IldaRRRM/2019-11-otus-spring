package ru.otus.homework.springlibrary.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.homework.springlibrary.domain.Genre;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("Проверка сохранения жанра в репозитории")
    public void shouldReturnSavedBeforeAuthorByName() {
        Genre expectedGenre = new Genre("Mr Ужасы" + System.currentTimeMillis());
        Mono<Genre> genreMono = genreRepository.save(expectedGenre);

        StepVerifier
                .create(genreMono)
                .assertNext(genre -> assertNotNull(genre.getId()))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Проверка поиска жанра по названию без учета регистра")
    public void shouldReturnAuthorByNameIgnoreCase() {
        String expectedName = "ЖАНР" + System.currentTimeMillis();
        Genre genre = new Genre(expectedName);
        genreRepository.save(genre).subscribe();

        StepVerifier.create(genreRepository.findGenreByNameIgnoreCase(expectedName.toLowerCase()))
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

}