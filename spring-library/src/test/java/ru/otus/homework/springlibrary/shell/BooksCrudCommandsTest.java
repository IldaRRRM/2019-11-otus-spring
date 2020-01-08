package ru.otus.homework.springlibrary.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.springlibrary.dao.BookDao;
import ru.otus.homework.springlibrary.dao.jdbc.AuthorDaoJdbc;
import ru.otus.homework.springlibrary.dao.jdbc.GenreDaoJdbc;
import ru.otus.homework.springlibrary.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BooksCrudCommandsTest {

    private static final Long INITIAL_BOOK_ID = -1L;

    @Autowired
    private BooksCrudCommands booksCrudCommands;
    @Autowired
    private GenreDaoJdbc genreDaoJdbc;
    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;
    @Autowired
    private BookDao bookDao;


    @Test
    @DisplayName("Проверка методов getAll() и Count()")
    void shouldReturnExpectedCountOfBooks() {
        assertThat((long) booksCrudCommands.getAllBooks().size()).isEqualTo(booksCrudCommands.getBooksCount());
    }

    @Test
    @DisplayName("Добавление информации о новой книги в основные(books, genres, authors) и связующие таблицы")
    void shouldAddNewRecordToBooksAuthorsGenresAndLinkedTables() {
        Long addedBookId = booksCrudCommands.addNewBook("newTestingBook", 1984, new String[]{"Оруэлл"}, new String[]{"жизнь"});
        assertThat(genreDaoJdbc.getGenresByBookId(addedBookId).size()).isEqualTo(1);
        assertThat(authorDaoJdbc.getAuthorsByBookId(addedBookId).size()).isEqualTo(1);
        assertThat(authorDaoJdbc.getAuthorsByBookId(addedBookId).size()).isEqualTo(1);
        assertThat(bookDao.getBookById(addedBookId).getName()).isEqualTo("newTestingBook");
        booksCrudCommands.deleteBookById(addedBookId);

    }

    @Test
    @DisplayName("Проверка обновление книги")
    void shouldUpdateBook() {

        Book beforeUpdatedBook = booksCrudCommands.getBookById(INITIAL_BOOK_ID);

        booksCrudCommands.getBookById(INITIAL_BOOK_ID);

        booksCrudCommands.updateBook(INITIAL_BOOK_ID, "новый автор", "новое имя", "новый жанр", 1920);

        assertThat(authorDaoJdbc.getAuthorsByBookId(INITIAL_BOOK_ID).size()).isEqualTo(2);
        assertThat(booksCrudCommands.getBookById(INITIAL_BOOK_ID).getAuthors().stream().anyMatch(author -> author.getName().equals("новый автор"))).isTrue();
        assertThat(booksCrudCommands.getBookById(INITIAL_BOOK_ID).getGenres().stream().anyMatch(genre -> genre.getName().equals("новый жанр"))).isTrue();
        assertThat(bookDao.getBookById(INITIAL_BOOK_ID).getName()).isEqualTo("новое имя");
        assertThat(bookDao.getBookById(INITIAL_BOOK_ID).getReleaseYear()).isEqualTo(1920);

        booksCrudCommands.updateBook(INITIAL_BOOK_ID, beforeUpdatedBook.getAuthors().get(0).getName(), beforeUpdatedBook.getName(), beforeUpdatedBook.getGenres().get(0).getName(), beforeUpdatedBook.getReleaseYear());
    }
}