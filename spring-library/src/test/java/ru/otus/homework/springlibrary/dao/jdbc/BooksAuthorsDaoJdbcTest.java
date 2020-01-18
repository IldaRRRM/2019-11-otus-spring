package ru.otus.homework.springlibrary.dao.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.springlibrary.domain.Book;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class BooksAuthorsDaoJdbcTest {
    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    private static final Long BOOK_ID_FOR_TESTING = -14L;

    @DisplayName("Возвращать ожидаемое количество книг")
    @Test
    void shouldReturnExpectedBookCount() {
        int expectedBookSize = bookDaoJdbc.getAll().size();
        assertThat(bookDaoJdbc.count()).isEqualTo(expectedBookSize);
    }

    @DisplayName("Добавление в базу новой книги")
    @Test
    void shouldReturnBookIdAfterInsertInDb() {
        Long insertedBook = bookDaoJdbc.insert(new Book(BOOK_ID_FOR_TESTING, "InsertedBook", 1970));
        assertThat(insertedBook).isEqualTo(BOOK_ID_FOR_TESTING);
        bookDaoJdbc.deleteById(BOOK_ID_FOR_TESTING);
    }

    @DisplayName("Получение книги по ее Id")
    @Test
    void shouldReturnBookById() {
        Book expectedBook = new Book(BOOK_ID_FOR_TESTING, "findestBook", 2020, new ArrayList<>(), new ArrayList<>());
        bookDaoJdbc.insert(expectedBook);
        Book actualBook = bookDaoJdbc.getBookById(BOOK_ID_FOR_TESTING);
        assertThat(actualBook).isEqualTo(expectedBook);
        bookDaoJdbc.deleteById(BOOK_ID_FOR_TESTING);
    }

    @DisplayName("Обновление добавленной книги в базу")
    @Test
    void shouldReturnUpdatedBook() {
        Book expectedBook = new Book(BOOK_ID_FOR_TESTING, "beforeUpdate", 1970, new ArrayList<>(), new ArrayList<>());
        bookDaoJdbc.insert(expectedBook);
        expectedBook.setName("afterUpdate");
        expectedBook.setReleaseYear(2020);
        bookDaoJdbc.update(expectedBook);
        assertThat(bookDaoJdbc.getBookById(BOOK_ID_FOR_TESTING)).isEqualTo(expectedBook);
        bookDaoJdbc.deleteById(BOOK_ID_FOR_TESTING);
    }

}