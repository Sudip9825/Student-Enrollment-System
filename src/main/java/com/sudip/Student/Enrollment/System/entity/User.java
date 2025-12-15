package com.sudip.Student.Enrollment.System.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter


@EntityListeners(AuditingEntityListener.class)
@Table(name="users")
public class User {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column(name = "username", nullable = false, length = 55,unique = true)
        private String username;

        @Column(name = "email", nullable = false, unique = true, length = 255)
        private String email;

        @Column(name = "password", nullable = false, length = 255)
        private String password;

        @Column(name = "role", nullable = false, length = 50)
        private String role;

        @CreatedDate
        @Column(name = "created_at", nullable = false)
        private LocalDateTime createdAt;

        @Column(name = "is_deleted", nullable = false)
        private boolean isDeleted = false;
    }


