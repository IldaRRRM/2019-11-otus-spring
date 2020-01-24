DROP TABLE IF EXISTS authors;
CREATE TABLE IF NOT EXISTS authors (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) UNIQUE,
    country VARCHAR(255)
);

DROP TABLE IF EXISTS books;
CREATE TABLE IF NOT EXISTS books (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    release_year INTEGER
);


DROP TABLE IF EXISTS genres;
CREATE TABLE IF NOT EXISTS genres (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

DROP TABLE IF EXISTS books_genres;
CREATE TABLE IF NOT EXISTS books_genres (
    book_id BIGINT,
    genre_id BIGINT,
    FOREIGN KEY(book_id) REFERENCES books(id) ON DELETE CASCADE,
    FOREIGN KEY(genre_id) REFERENCES genres(id) ON DELETE CASCADE,
    PRIMARY KEY(book_id, genre_id)
);

DROP TABLE IF EXISTS authors_books;
CREATE TABLE IF NOT EXISTS authors_books (
    author_id BIGINT,
    book_id BIGINT,
    FOREIGN KEY(author_id) REFERENCES authors(id) ON DELETE CASCADE,
    FOREIGN KEY(book_id) REFERENCES books(id) ON DELETE CASCADE,
    PRIMARY KEY(author_id, book_id)
);

DROP TABLE IF EXISTS comments;
CREATE TABLE IF NOT EXISTS comments (
    id SERIAL NOT NULL PRIMARY KEY,
    book_id BIGINT,
    comment VARCHAR(2048)
);




