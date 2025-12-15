package com.sudip.Student.Enrollment.System.dto.userdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserReq {
    @NotBlank(message = "id required")
    private String id;

    @NotBlank(message = "username ")
    private String username;
    @NotBlank(message = "pw required")

    @Email(message = "correct email format required")
    private String email;
}
