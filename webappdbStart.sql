CREATE DATABASE IF NOT EXISTS webappdb;

USE webappdb;

CREATE TABLE IF NOT EXISTS userstable (
                     id INT KEY NOT NULL AUTO_INCREMENT,
                     username VARCHAR(255) UNIQUE NOT NULL,
                     password VARCHAR(255) NOT NULL,
                     name VARCHAR(255) NOT NULL
);

INSERT INTO userstable VALUES (DEFAULT, 'admin', '$2y$12$HwTIgwJ.99svRGrIgPNhDOe4AjqsS0gC5UqAbWR3SHkp/YjMEXLna', 'Admin');