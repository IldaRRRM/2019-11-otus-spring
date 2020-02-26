package ru.otus.homework.springlibrary.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework.springlibrary.domain.Author;

import java.util.Optional;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

    Optional<Author> findAuthorByName(String name);
}
