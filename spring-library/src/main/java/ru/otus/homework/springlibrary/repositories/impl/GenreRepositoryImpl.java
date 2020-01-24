package ru.otus.homework.springlibrary.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.springlibrary.domain.Genre;
import ru.otus.homework.springlibrary.repositories.GenreRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Repository
public class GenreRepositoryImpl implements GenreRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Long count() {
        return entityManager.createQuery("SELECT COUNT(g.id) FROM Genre g", Long.class).getResultList().get(0);
    }

    @Transactional
    @Override
    public List<Genre> getAllGenres() {
        return entityManager.createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
    }

    @Transactional
    @Override
    public void deleteGenreById(Long genreId) {
        Query query = entityManager.createQuery("DELETE FROM Genre g WHERE g.id = :id");
        query.setParameter("id", genreId);
        query.executeUpdate();
    }

    @Transactional
    @Override
    public Optional<Genre> getGenreById(Long genreId) {
        return Optional.ofNullable(entityManager.find(Genre.class, genreId));
    }

    @Transactional
    @Override
    public Genre save(Genre genre) {
        if (genre.getId() == null) {
            entityManager.persist(genre);
        } else {
            return entityManager.merge(genre);
        }
        return genre;
    }

    @Transactional
    @Override
    public void updateGenreById(Long id, Map<String, String> updatedFields) {
        if (updatedFields.containsKey("name")) {
            Genre genre = getGenreById(id).orElseThrow();
            genre.setName(updatedFields.get("name"));
            entityManager.merge(genre);
        }
    }
}
