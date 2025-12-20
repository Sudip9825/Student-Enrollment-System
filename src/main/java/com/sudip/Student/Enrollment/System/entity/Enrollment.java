package com.sudip.Student.Enrollment.System.entity;

import com.sudip.Student.Enrollment.System.enums.EnrollmentStatus;
import com.sudip.Student.Enrollment.System.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name="enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_id")
    private Course course;

    @Column(name="enrollment_date")
    private LocalDateTime enrollmentDate;

    @Enumerated(EnumType.STRING)
    @Column( name="status",nullable = false)
    private EnrollmentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;
    @Column(name="amount")
    private BigDecimal amount;
    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedDate;

}
