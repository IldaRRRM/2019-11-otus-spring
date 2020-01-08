CREATE TABLE IF NOT EXISTS books (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    release_year INTEGER
);

CREATE TABLE IF NOT EXISTS authors (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) UNIQUE,
    country VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS genres (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

CREATE TABLE IF NOT EXISTS books_genres (
    book_id BIGINT,
    genre_id BIGINT,
    FOREIGN KEY(book_id) REFERENCES books(id) ON DELETE CASCADE,
    FOREIGN KEY(genre_id) REFERENCES genres(id) ON DELETE CASCADE,
    PRIMARY KEY(book_id, genre_id)
);

CREATE TABLE IF NOT EXISTS authors_books (
    author_id BIGINT,
    book_id BIGINT,
    FOREIGN KEY(author_id) REFERENCES authors(id) ON DELETE CASCADE,
    FOREIGN KEY(book_id) REFERENCES books(id) ON DELETE CASCADE,
    PRIMARY KEY(author_id, book_id)
);



