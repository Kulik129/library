CREATE TABLE persons
(
    id            SERIAL PRIMARY KEY,
    uuid          uuid,
    person_name          VARCHAR(100),
    date_of_birth Date,
    rating        DOUBLE PRECISION,
    book_id VARCHAR(100)
);
