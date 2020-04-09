package ru.otus.homework.springbatch.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework.springbatch.domain.mongo.MongoGenre;

@Repository
public interface MongoGenreRepository extends MongoRepository<MongoGenre, String> {

}
