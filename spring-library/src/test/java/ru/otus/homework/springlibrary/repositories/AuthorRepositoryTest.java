package ru.otus.homework.springlibrary.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import ru.otus.homework.springlibrary.domain.Author;

@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("Проверка поиска автора по имени")
    void shouldReturnSavedBeforeAuthorByName() {
        Author expectedAuthor = new Author("Akunin");
        authorRepository.save(expectedAuthor);
        Mono<Author> boris = authorRepository.findAuthorByNameIgnoreCase("akunin");
    }
}