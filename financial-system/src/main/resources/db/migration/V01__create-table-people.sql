CREATE TABLE people (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL,
    street VARCHAR(100),
    number VARCHAR(20),
    complement VARCHAR(100),
    district VARCHAR(100),
    zip_code VARCHAR(20),
    city VARCHAR(100),
    state VARCHAR(50),

    primary key(id)
);