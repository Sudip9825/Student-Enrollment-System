package com.sudip.Student.Enrollment.System.dto.Enrollmentdto;

import com.sudip.Student.Enrollment.System.enums.EnrollmentStatus;
import com.sudip.Student.Enrollment.System.enums.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class EnrollmentResponseDto {
     private Integer enrollmentId;

     private Integer courseId;

    private String courseTitle;

    private EnrollmentStatus status;

    private PaymentStatus paymentStatus;
    @NotBlank(message = "amount cannot be blank")
    private BigDecimal amount;
    private LocalDateTime enrollmentDate;

}
