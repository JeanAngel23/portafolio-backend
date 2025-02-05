CREATE TABLE contact (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15),
    linkedin_url VARCHAR(255),
    github_url VARCHAR(255),
    address VARCHAR(255)
);
