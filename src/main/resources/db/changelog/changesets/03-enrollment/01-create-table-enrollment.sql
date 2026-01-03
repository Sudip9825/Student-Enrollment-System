-- liquibase formatted sql
-- changeset sudip.dahal:suppler-create-v1     context:dev
-- preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `enrollment` (
  id INT AUTO_INCREMENT PRIMARY KEY,

user_id INT NOT NULL,
 course_id INT NOT NULL,

  enrollment_date TIMESTAMP NOT NULL,

   status VARCHAR(20) NOT NULL,
    payment_status VARCHAR(20) NOT NULL,

    amount DECIMAL(10,2) NOT NULL,

    created_at TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_enrollment_user
    FOREIGN KEY (user_id) REFERENCES users(id)
                                                   ON DELETE CASCADE,

    CONSTRAINT fk_enrollment_course
    FOREIGN KEY (course_id) REFERENCES courses(id)
                                                   ON DELETE CASCADE,

    CONSTRAINT uq_user_course
    UNIQUE (user_id, course_id)
    );
