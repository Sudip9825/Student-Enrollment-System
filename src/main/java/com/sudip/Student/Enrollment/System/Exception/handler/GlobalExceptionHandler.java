package com.sudip.Student.Enrollment.System.Exception.handler;

import com.sudip.Student.Enrollment.System.Exception.DuplicateException;
import com.sudip.Student.Enrollment.System.Exception.InvalidTokenException;
import com.sudip.Student.Enrollment.System.Exception.NotFoundException;
import com.sudip.Student.Enrollment.System.core.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ApiResponse<?>> handleException(DuplicateException ex) {
        ApiResponse<?> apiResponse = new ApiResponse<>(false,ex.getMessage(), HttpStatus.CONFLICT.value(), LocalDateTime.now());
        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(NotFoundException e) {
        ApiResponse<?> apiRespone = new ApiResponse<>( false, e.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
        return new ResponseEntity<>(apiRespone, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ApiResponse<?>> handleInvalidTokenException(InvalidTokenException e) {
        ApiResponse<?> apiResponse = new ApiResponse<>(false,e.getMessage(), HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now());
        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
    }
}
