create database sprint_tracker;

use sprint_tracker;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    phoneNumber VARCHAR(15),
    password VARCHAR(255) NOT NULL
);