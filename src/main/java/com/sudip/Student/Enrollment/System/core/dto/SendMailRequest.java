package com.sudip.Student.Enrollment.System.core.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMailRequest {
    @NotBlank(message = "recipient mail is required")
    private String recipient;
    @NotBlank(message="Subject is requiered")
    private String subject;
    @NotBlank(message = "message body is required")
    private String message;
}
