package ru.otus.homework.springlibrary.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework.springlibrary.domain.Book;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
