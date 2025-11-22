CREATE TABLE transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    due_date DATE,
    payment_date DATE,
    type VARCHAR(50),
    note TEXT,
    amount DECIMAL(10, 2),
    active TINYINT(1) NOT NULL,
    category_id BIGINT,
    person_id BIGINT,

    CONSTRAINT fk_transaction_category FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT fk_transaction_person FOREIGN KEY (person_id) REFERENCES people(id)
);
