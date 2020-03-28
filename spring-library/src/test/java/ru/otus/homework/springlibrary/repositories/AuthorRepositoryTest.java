package ru.otus.homework.springlibrary.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.homework.springlibrary.domain.Author;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@DataMongoTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("Проверка сохранения автора в репозитории")
    public void shouldReturnSavedBeforeAuthorByName() {
        Author expectedAuthor = new Author("Mr Akunin" + System.currentTimeMillis());
        Mono<Author> savedAuthor = authorRepository.save(expectedAuthor);

        StepVerifier
                .create(savedAuthor)
                .assertNext(author -> assertNotNull(expectedAuthor.getId()))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Проверка поиска автора без учета регистра")
    public void shouldReturnAuthorByNameIgnoreCase() {
        String expectedName = "CASTANEDO" + System.currentTimeMillis();
        Author expectedAuthor = new Author(expectedName);
        authorRepository.save(expectedAuthor).subscribe();

        StepVerifier.create(authorRepository.findAuthorByNameIgnoreCase(expectedName.toLowerCase()))
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }
}