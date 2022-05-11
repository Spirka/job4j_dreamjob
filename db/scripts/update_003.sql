CREATE TABLE if not exists users (id SERIAL PRIMARY KEY,
                                  name TEXT,
                                  email VARCHAR,
                                  password TEXT
);

ALTER TABLE users ADD CONSTRAINT email_unique UNIQUE (email)
