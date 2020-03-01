package ru.otus.homework.springlibrary.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ru.otus.homework.springlibrary.domain.Author;

import java.util.Optional;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

    @Query(value = "{'name': {$regex : ?0, $options: 'i'}}")
    Optional<Author> findAuthorByNameIgnoreName(String name);
}
