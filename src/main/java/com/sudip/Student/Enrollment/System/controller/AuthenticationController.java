package com.sudip.Student.Enrollment.System.controller;

import com.sudip.Student.Enrollment.System.core.dto.ApiResponse;
import com.sudip.Student.Enrollment.System.core.dto.LoginRequest;
import com.sudip.Student.Enrollment.System.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;
    @PostMapping("/login")
    public  ResponseEntity<ApiResponse<?>> loginuser(@RequestBody @Valid  LoginRequest loginRequest){
       ApiResponse<?> apiResponse = authenticationService.authenticate(loginRequest);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping("/refresh/token")
    public ResponseEntity<ApiResponse<?>> refreshToken(HttpServletRequest request ,HttpServletResponse response){
        ApiResponse<?> apiResponse = authenticationService.refreshToken(request,response);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
