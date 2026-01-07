CREATE TABLE IF NOT EXISTS tootajad (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nimi VARCHAR(255),
    email VARCHAR(255),
    telefon VARCHAR(255),
    elukoht VARCHAR(255),
    lisatud TIMESTAMP,
    muudetud TIMESTAMP
);