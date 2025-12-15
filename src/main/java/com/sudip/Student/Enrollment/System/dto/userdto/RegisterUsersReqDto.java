package com.sudip.Student.Enrollment.System.dto.userdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUsersReqDto {
    @NotBlank(message = "username is needed")
    private String username;
    @NotBlank(message = "email is needed")
    @Email(message = "invalid email format")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Role is required")
    @Pattern(regexp = "USER|ADMIN", message = "Role must be either USER or ADMIN")
    private String role;
}
