package com.sudip.Student.Enrollment.System.service;

import com.sudip.Student.Enrollment.System.core.dto.ApiResponse;

public interface EnrollmentService {
    public ApiResponse<?>enrollCourse(Integer courseId);//logged in user to enroll
    public ApiResponse<?> getMyEnrollments();//this function is used to show all the courses a logged in user is enrolled in
    public ApiResponse<?> isUserEnrolled(Integer courseId);// to check whether user is already or not
    public ApiResponse<?> getEnrollmentById(Integer enrollmentId);//to view enrollment status and payment status
    public ApiResponse<?> getMyEnrollmentsByCourse(Integer courseId);//to see how many users are enrolled in course by admin

}
