package ru.otus.homework.springlibrary.repositories.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework.springlibrary.domain.Author;
import ru.otus.homework.springlibrary.repositories.AuthorRepository;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(AuthorRepositoryImpl.class)
class AuthorRepositoryImplTest {

    @Autowired
    private AuthorRepository repository;

    @Test
    @DisplayName("Проверка методов count и getAll")
    void shouldGetExpectedCountOfAuthors() {
        assertThat(repository.count()).isEqualTo(repository.getAllAuthors().size());
    }

    @Test
    @DisplayName("Проверка удаления автора из БД по его Id")
    void shouldDeleteAuthorById() {
        Author savedAuthor = repository.save(new Author("testovichN1", "Russia"));
        assertThat(repository.count()).isEqualTo(3);
        repository.deleteAuthorById(savedAuthor.getId());
        assertThat(repository.count()).isEqualTo(2);
    }

    @Test
    @DisplayName("Тест по получению автора по его Id")
    void shouldReturnTheSameAuthorWhichHasBeenSavedBefore() {
        Author savedAuthor = repository.save(new Author("secondAuthor", "Canada"));
        assertThat(repository.getAuthorById(savedAuthor.getId())).get().isEqualTo(savedAuthor);
    }

    @Test
    @DisplayName("Проверка обновления автора по его id ")
    void updateAuthorById() {
        Author thirdAuthor = repository.save(new Author("ThirdAuthor"));
        Map<String, String> updatedFields = new HashMap<>();
        updatedFields.put("name", "newAuthor");
        updatedFields.put("country", "newCountry");
        repository.updateAuthorById(thirdAuthor.getId(), updatedFields);
        Author updatedAuthor = repository.getAuthorById(thirdAuthor.getId()).orElseThrow();
        assertThat(updatedAuthor.getCountry()).isEqualTo("newCountry");
        assertThat(updatedAuthor.getName()).isEqualTo("newAuthor");
        assertThat(updatedAuthor.getId()).isEqualTo(thirdAuthor.getId());
    }
}