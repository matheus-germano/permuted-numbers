CREATE TYPE difficulty AS ENUM ('easy', 'normal', 'hard');

CREATE TABLE room (
    id UUID NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),
    name TEXT NOT NULL,
    password TEXT,
    difficulty difficulty,
    createdAt DATE NOT NULL
);