-- liquibase formatted sql
-- changeset sudip.dahal:suppler-create-v1     context:dev
-- preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `user` (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         username VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL,
                         role VARCHAR(50) NOT NULL,
                         created_at TIMESTAMP NOT NULL,
                         is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);
