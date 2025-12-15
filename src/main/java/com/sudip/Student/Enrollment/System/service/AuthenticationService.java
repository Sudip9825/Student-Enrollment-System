package com.sudip.Student.Enrollment.System.service;

import com.sudip.Student.Enrollment.System.core.dto.ApiResponse;
import com.sudip.Student.Enrollment.System.core.dto.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    public ApiResponse<?> authenticate(LoginRequest loginRequest);
    public ApiResponse<?> refreshToken(HttpServletRequest request, HttpServletResponse response);
}
