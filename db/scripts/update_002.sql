CREATE TABLE candidate (
                      id SERIAL PRIMARY KEY,
                      name TEXT,
                      description TEXT,
                      photo BYTEA,
                      date_of_birth TIMESTAMP
);
