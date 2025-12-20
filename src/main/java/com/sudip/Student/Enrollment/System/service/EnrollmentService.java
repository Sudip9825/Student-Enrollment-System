package com.sudip.Student.Enrollment.System.service;

import com.sudip.Student.Enrollment.System.core.dto.ApiResponse;

public interface EnrollmentService {
    ApiResponse<?>enrollCourse(Integer courseId);
}
