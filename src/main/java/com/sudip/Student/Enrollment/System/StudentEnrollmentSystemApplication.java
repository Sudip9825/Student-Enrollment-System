package com.sudip.Student.Enrollment.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class StudentEnrollmentSystemApplication {

	public static void main(String[] args) {

        SpringApplication.run(StudentEnrollmentSystemApplication.class, args);
	}

}
