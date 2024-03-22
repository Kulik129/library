CREATE TABLE issue
(
    id SERIAL PRIMARY KEY,
    UUID uuid,
    issued_at DATE,
    book_id uuid,
    reader_id uuid
);