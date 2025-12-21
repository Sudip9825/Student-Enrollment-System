package com.sudip.Student.Enrollment.System.controller;

import com.sudip.Student.Enrollment.System.core.dto.ApiResponse;
import com.sudip.Student.Enrollment.System.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enrollment")

public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;
    @PostMapping("/enroll{id}")
    public ResponseEntity<ApiResponse<?>> enroll(@PathVariable Integer id) {
        ApiResponse<?> apiResponse = enrollmentService.enrollCourse(id);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
