package ru.otus.homework.springlibrary.repositories;

import ru.otus.homework.springlibrary.domain.Author;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AuthorRepository {

    Long count();

    List<Author> getAllAuthors();

    void deleteAuthorById(Long authorId);

    Optional<Author> getAuthorById(Long authorId);

    Author save(Author author);

    void updateAuthorById(Long authorId, Map<String, String> updatedFields);

}
