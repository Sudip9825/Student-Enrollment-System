package com.sudip.Student.Enrollment.System.controller;

import com.sudip.Student.Enrollment.System.core.dto.ApiResponse;
import com.sudip.Student.Enrollment.System.entity.Enrollment;
import com.sudip.Student.Enrollment.System.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/enrollments/my")
    public ResponseEntity<ApiResponse<?>> enrollments() {
        ApiResponse<?> apiResponse = enrollmentService.getMyEnrollments();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
