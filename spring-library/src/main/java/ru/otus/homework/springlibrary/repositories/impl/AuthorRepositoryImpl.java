package ru.otus.homework.springlibrary.repositories.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.springlibrary.domain.Author;
import ru.otus.homework.springlibrary.repositories.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Long count() {
        return entityManager.createQuery("SELECT COUNT(a.id) from Author a", Long.class).getResultList().get(0);
    }

    @Override
    public List<Author> getAllAuthors() {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }

    @Transactional
    @Override
    public void deleteAuthorById(Long authorId) {
        Query query = entityManager.createQuery("DELETE FROM Author a WHERE a.id = :id");
        query.setParameter("id", authorId);
        query.executeUpdate();
    }

    @Transactional
    @Override
    public Optional<Author> getAuthorById(Long authorId) {
        return Optional.ofNullable(entityManager.find(Author.class, authorId));
    }

    @Transactional
    @Override
    public Author save(Author author) {
        if (author.getId() == null) {
            entityManager.persist(author);
        } else {
            return entityManager.merge(author);
        }
        return author;
    }

    @Transactional
    @Override
    public void updateAuthorById(Long authorId, Map<String, String> updatedFields) {
        Author preparedAuthor = getAuthorById(authorId).orElseThrow();
        if (updatedFields.containsKey("name")) {
            preparedAuthor.setName(updatedFields.get("name"));
        }
        if (updatedFields.containsKey("country")) {
            preparedAuthor.setCountry(updatedFields.get("country"));
        }
        entityManager.merge(preparedAuthor);
    }
}
