package com.sudip.Student.Enrollment.System.entity;

import com.sudip.Student.Enrollment.System.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;

    @Column(name="amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "payment_gateway", nullable = false)
    private String paymentGateway;

    @Column(name = "transaction_id", unique = true)
    private String transactionId;

    @CreatedDate
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status" , nullable = false)
    private PaymentStatus paymentStatus;

}
