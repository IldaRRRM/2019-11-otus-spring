package ru.otus.homework.springbatch.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework.springbatch.domain.mongo.MongoBook;

@Repository
public interface MongoBookRepository extends MongoRepository<MongoBook, String> {

}
