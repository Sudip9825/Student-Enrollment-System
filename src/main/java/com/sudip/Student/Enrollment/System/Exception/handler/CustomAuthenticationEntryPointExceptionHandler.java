package com.sudip.Student.Enrollment.System.Exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sudip.Student.Enrollment.System.core.dto.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;



import java.io.IOException;
import java.time.LocalDateTime;


@Component
public class CustomAuthenticationEntryPointExceptionHandler implements AuthenticationEntryPoint {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        var error = new ApiResponse<>(
                false,
                "Authentication Required: " + authException.getMessage(),
                HttpServletResponse.SC_UNAUTHORIZED,
                LocalDateTime.now()
        );

        response.getWriter().write(objectMapper.writeValueAsString(error));
    }
}