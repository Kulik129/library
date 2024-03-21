CREATE TABLE author
(
    id            SERIAL PRIMARY KEY,
    uuid          uuid,
    name          VARCHAR(100),
    date_of_birth Date,
    preview       VARCHAR(255),
    rating        DOUBLE PRECISION
);

CREATE TABLE book
(
    id                SERIAL primary key,
    uuid              uuid,
    title             varchar(150),
    description       text,
    release_date      Date,
    publishing_office VARCHAR(50),
    series            VARCHAR(50),
    isbn              VARCHAR(100),
    pages             INT,
    author_id         INT REFERENCES author (id) ON DELETE SET NULL
);