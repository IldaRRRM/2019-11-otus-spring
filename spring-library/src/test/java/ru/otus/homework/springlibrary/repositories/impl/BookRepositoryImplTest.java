package ru.otus.homework.springlibrary.repositories.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework.springlibrary.domain.Book;
import ru.otus.homework.springlibrary.domain.Comment;
import ru.otus.homework.springlibrary.repositories.BookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(BookRepositoryImpl.class)
class BookRepositoryImplTest {

    @Autowired
    private BookRepository bookRepository;

    @DisplayName("Проверка метода count")
    @Test
    void shouldReturnExpectedCountOfBooks() {
        assertThat(bookRepository.count()).isEqualTo(bookRepository.getAllBooks().size());
    }


    @Test
    @DisplayName("Проверка метода getAll со всеми данными")
    void shouldReturnAllBooksWithAllInfo() {
        List<Book> allBooks = bookRepository.getAllBooks();
        allBooks.forEach(book -> {
            assertThat(book.getAuthors().size() > 0).isTrue();
            assertThat(book.getGenres().size() > 0).isTrue();
        });
    }

    @Test
    @DisplayName("Проверка обновления книги по id")
    void updateBookById() {
        Book springBook = bookRepository.save(new Book("SpringBook", 123, null, null));

        bookRepository.updateBookById(springBook.getId(), "Таска", 2020, "Jpql", "horror");

        Book updatedBook = bookRepository.getBookById(springBook.getId()).orElseThrow();

        assertThat(updatedBook.getAuthors().size() > 0).isTrue();
        assertThat(updatedBook.getGenres().size() > 0).isTrue();
        assertThat(updatedBook.getName()).isEqualTo("Jpql");
        assertThat(updatedBook.getReleaseYear()).isEqualTo(2020);
    }

    @Test
    @DisplayName("Проверка удаления книги по Id")
    void shouldDeleteBookById() {
        Book springBook = bookRepository.save(new Book("OtherTestBook", 1234, null, null));
        Long countAfterAdd = bookRepository.count();
        bookRepository.deleteBookById(springBook.getId());
        assertThat(bookRepository.count()).isEqualTo(countAfterAdd - 1);
    }

    @Test
    @DisplayName("Проверка добавления комментария к книге")
    void shouldAddCommentToBook() {
        Book springBook = bookRepository.save(new Book("SpringBook", 123, null, null));
        bookRepository.addCommentToBook(springBook.getId(), new Comment("Неплохая книга, ДА!"));
        Book bookWithComment = bookRepository.getBookById(springBook.getId()).orElseThrow();
        assertThat(bookWithComment.getComments().get(0).getComment()).isEqualTo("Неплохая книга, ДА!");
    }
}