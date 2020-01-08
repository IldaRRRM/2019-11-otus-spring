INSERT INTO books (id, name, release_year) values (-1, 'testing', 1984) ON CONFLICT ON CONSTRAINT books_pkey DO NOTHING;
INSERT INTO authors (id, name, country) values (-1, 'testovich', 'mother_rus') ON CONFLICT ON CONSTRAINT authors_pkey DO NOTHING;
INSERT INTO genres (id, name) values (-1, 'genrevovich') ON CONFLICT ON CONSTRAINT genres_pkey DO NOTHING;
INSERT INTO authors_books (author_id, book_id) values (-1, -1) ON CONFLICT ON CONSTRAINT authors_books_pkey DO NOTHING;
INSERT INTO books_genres (book_id, genre_id) values (-1, -1) ON CONFLICT ON CONSTRAINT books_genres_pkey DO NOTHING;

INSERT INTO books (id, name, release_year) values (-2, 'second', 1999) ON CONFLICT ON CONSTRAINT books_pkey DO NOTHING;
INSERT INTO authors (id, name, country) values (-2, 'testovich2', 'mother_rus2') ON CONFLICT ON CONSTRAINT authors_pkey DO NOTHING;
INSERT INTO genres (id, name) values (-2, 'comedy') ON CONFLICT ON CONSTRAINT genres_pkey DO NOTHING;
INSERT INTO authors_books (author_id, book_id) values (-2, -2) ON CONFLICT ON CONSTRAINT authors_books_pkey DO NOTHING;
INSERT INTO books_genres (book_id, genre_id) values (-2, -2) ON CONFLICT ON CONSTRAINT books_genres_pkey DO NOTHING;
