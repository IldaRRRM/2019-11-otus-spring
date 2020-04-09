package ru.otus.homework.springbatch.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework.springbatch.domain.jpa.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
