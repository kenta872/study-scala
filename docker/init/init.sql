DROP SCHEMA IF EXISTS sample_db;
CREATE SCHEMA sample_db;
USE sample_db;

DROP TABLE IF EXISTS user_tbl;
CREATE TABLE user_tbl (
    `id` BIGINT AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO user_tbl (name, password) VALUES ('user1', 'password1');
INSERT INTO user_tbl (name, password) VALUES ('user2', 'password2');