package ru.otus.homework.springlibrary.shell;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BooksCrudCommandsTest {

//    @Autowired
//    private BooksCrudCommands booksCrudCommands;
//    @Autowired
//    private BooksCrudService bookService;
//
//    @Test
//    @DisplayName("Проверка методов getAll() и Count()")
//    void shouldReturnExpectedCountOfBooks() {
//        assertThat((long) booksCrudCommands.getAllBooks().size()).isEqualTo(booksCrudCommands.getBooksCount());
//    }
//
//    @Test
//    @DisplayName("Добавление информации о новой книги в основные(books, genres, authors) и связующие таблицы")
//    void shouldAddNewRecordToBooksAuthorsGenresAndLinkedTables() {
//        String addedBookId = booksCrudCommands.addNewBook("newTestingBook", 1984, new String[]{"Оруэлл"}, new String[]{"жизнь"});
//        assertThat(bookService.getBookById(addedBookId).getName()).isEqualTo("newTestingBook");
//        booksCrudCommands.deleteBookById(addedBookId);
//
//    }
//
//    @Test
//    @DisplayName("Проверка обновление книги")
//    void shouldUpdateBook() {
//
//        String addedBookId = booksCrudCommands.addNewBook("newTestingBook", 1984, new String[]{"Джоныч"}, new String[]{"жизнь"});
//
//        Book beforeUpdatedBook = booksCrudCommands.getBookById(addedBookId);
//
//        booksCrudCommands.getBookById(addedBookId);
//
//        booksCrudCommands.updateBook(addedBookId, "новый автор", "новое имя", "новый жанр", 1920);
//
//        assertThat(booksCrudCommands.getBookById(addedBookId).getAuthors().stream().anyMatch(author -> author.getName().equals("новый автор"))).isTrue();
//        assertThat(booksCrudCommands.getBookById(addedBookId).getGenres().stream().anyMatch(genre -> genre.getName().equals("новый жанр"))).isTrue();
//        assertThat(booksCrudCommands.getBookById(addedBookId).getReleaseYear()).isEqualTo(1920);
//        assertThat(booksCrudCommands.getBookById(addedBookId).getName()).isEqualTo("новое имя");
//    }
//
//    @Test
//    @DisplayName("Проверка просмотра комментариев книги по ее Id")
//    void shouldReturnAllCommentsOfBook() {
//        String addedBookId = booksCrudCommands.addNewBook("BookWithComment", 1984, new String[]{"Кендрик"}, new String[]{"жизнь"});
//        booksCrudCommands.addCommentToBook(addedBookId, "норм книга");
//        assertThat(booksCrudCommands.showCommentOfBook(addedBookId).get(0).getComment()).isEqualTo("норм книга");
//    }
//
//    @Test
//    @DisplayName("Проверка добавлении комментария к книге")
//    void shouldAddNewCommentToBook() {
//        String addedBookId = booksCrudCommands.addNewBook("BookWithComment", 1984, new String[]{"Ломар"}, new String[]{"жизнь"});
//        int expectedCommentSize = booksCrudCommands.showCommentOfBook(addedBookId).size() + 1;
//        booksCrudCommands.addCommentToBook(addedBookId, "Новый комментарий");
//        assertThat(booksCrudCommands.showCommentOfBook(addedBookId).size()).isEqualTo(expectedCommentSize);
//    }

}