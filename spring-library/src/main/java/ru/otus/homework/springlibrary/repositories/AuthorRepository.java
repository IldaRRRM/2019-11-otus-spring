package ru.otus.homework.springlibrary.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.otus.homework.springlibrary.domain.Author;

@Repository
public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

    @Query(value = "{'name': {$regex : ?0, $options: 'i'}}")
    Mono<Author> findAuthorByNameIgnoreCase(String name);
}
