package ru.otus.homework.springlibrary.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework.springlibrary.domain.Genre;

import java.util.Optional;

@Repository
public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {
    //TODO ПОфиксить
    @Query(value = "{'name': {$regex : ?0, $options: 'i'}}")
    Optional<Genre> findGenreByNameIgnoreCase(String name);
}
