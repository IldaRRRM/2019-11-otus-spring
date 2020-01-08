package ru.otus.homework.springlibrary.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.springlibrary.dao.AuthorDao;
import ru.otus.homework.springlibrary.domain.Author;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCrudCommands {

    private final AuthorDao authorDao;

    @ShellMethod(value = "add new Author", key = {"insa", "add new Author"})
    public void addNewAuthor(@ShellOption String authorName, @ShellOption String authorCountry) {
        authorDao.insert(new Author(authorName, authorCountry));
    }

    @ShellMethod(value = "delete author from db", key = {"delAuth", "dela"})
    public void deleteAuthorFromDb(@ShellOption Long authorId) {
        authorDao.deleteById(authorId);
    }

}
