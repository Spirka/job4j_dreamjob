--Create database
-- CREATE DATABASE dreamjob;

CREATE TABLE if not exists post (
                      id SERIAL PRIMARY KEY,
                      name TEXT,
                      description TEXT,
                      visible BOOLEAN,
                      created TIMESTAMP
);

ALTER TABLE post ADD COLUMN city_id INT;
