package ru.otus.homework.springlibrary.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.springlibrary.domain.Author;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    @DisplayName("Проверка поиска автора по имени")
    void shouldReturnSavedBeforeAuthorByName() {
        Author expectedAuthor = new Author("Akunin");
        authorRepository.save(expectedAuthor);
        Author boris = authorRepository.findAuthorByNameIgnoreName("akunin").orElseThrow();
        assertThat(boris).isEqualTo(expectedAuthor);
        authorRepository.delete(boris);
    }
}