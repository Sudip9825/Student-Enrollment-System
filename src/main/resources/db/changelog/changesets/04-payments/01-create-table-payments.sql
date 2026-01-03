-- liquibase formatted sql
-- changeset sudip.dahal:suppler-create-v1     context:dev
-- preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `payments` (
 id INT AUTO_INCREMENT PRIMARY KEY,

 enrollment_id INT NOT NULL,

  amount DECIMAL(10,2) NOT NULL,

    payment_gateway VARCHAR(50) NOT NULL,

    transaction_id VARCHAR(100),

    status VARCHAR(20) NOT NULL,

    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,


    CONSTRAINT fk_payment_enrollment
    FOREIGN KEY (enrollment_id)
    REFERENCES enrollment(id),

    CONSTRAINT uq_transaction
    UNIQUE (transaction_id)
    );
