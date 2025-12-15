-- liquibase formatted sql
-- changeset sudip.dahal:suppler-create-v1     context:dev
-- preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `courses` (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(200) NOT NULL,
                         description TEXT,
                         category VARCHAR(100),
                         price DECIMAL(10,2) DEFAULT 0,
                         is_free BOOLEAN NOT NULL DEFAULT TRUE,
                         created_at TIMESTAMP ,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
