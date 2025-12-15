package com.sudip.Student.Enrollment.System.dto.userdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AuthenticationResponse {
private String accessToken;
private String refreshToken;

}
