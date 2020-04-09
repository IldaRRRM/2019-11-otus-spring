INSERT INTO books (id, name, release_year) values (-1, 'testing', 1984);
INSERT INTO authors (id, name, country) values (-1, 'testovich', 'mother_rus');
INSERT INTO genres (id, name) values (-1, 'genrevovich');
INSERT INTO authors_books (author_id, book_id) values (-1, -1);
INSERT INTO books_genres (book_id, genre_id) values (-1, -1);
INSERT INTO comments (id, book_id, comment) values (-1, -1, 'норм книга');

INSERT INTO books (id, name, release_year) values (-2, 'testing2', 1985);
INSERT INTO authors (id, name, country) values (-2, 'testovich2', 'mother_rus2');
INSERT INTO genres (id, name) values (-2, 'genrevovich2');
INSERT INTO authors_books (author_id, book_id) values (-2, -2);
INSERT INTO books_genres (book_id, genre_id) values (-2, -2);
INSERT INTO comments (id, book_id, comment) values (-2, -2, 'Не норм');
