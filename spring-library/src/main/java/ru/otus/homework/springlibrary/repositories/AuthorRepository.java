package ru.otus.homework.springlibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework.springlibrary.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
