package ru.otus.homework.springlibrary.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework.springlibrary.domain.Genre;

import java.util.Optional;

@Repository
public interface GenreRepository extends MongoRepository<Genre, String> {

    Optional<Genre> findGenreByName(String name);
}
