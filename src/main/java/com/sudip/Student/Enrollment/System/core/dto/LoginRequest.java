package com.sudip.Student.Enrollment.System.core.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank( message="email needed")
    private String email;
    @NotBlank(message ="password required")
    private String password;
}
