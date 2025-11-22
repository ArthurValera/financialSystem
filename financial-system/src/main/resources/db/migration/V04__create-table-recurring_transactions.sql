CREATE TABLE recurring_transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,

    start_date DATE NOT NULL,
    end_date DATE,

    recurrence_type VARCHAR(50) NOT NULL,
    transaction_type VARCHAR(50) NOT NULL,

    category_id BIGINT,
    person_id BIGINT,

    active BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT fk_recurring_category
        FOREIGN KEY (category_id) REFERENCES categories(id),

    CONSTRAINT fk_recurring_person
        FOREIGN KEY (person_id) REFERENCES people(id)
) ENGINE=InnoDB;