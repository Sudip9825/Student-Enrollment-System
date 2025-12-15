package com.sudip.Student.Enrollment.System.service;

import com.sudip.Student.Enrollment.System.core.dto.ApiResponse;
import com.sudip.Student.Enrollment.System.core.dto.PaginationDto;
import com.sudip.Student.Enrollment.System.dto.coursedto.AddCourseReq;

import com.sudip.Student.Enrollment.System.dto.coursedto.UpdateCourseReq;
import com.sudip.Student.Enrollment.System.dto.coursedto.ViewCourseRequest;

public interface CourseService {
    ApiResponse<?>addCourse(AddCourseReq addCourseReq);
    ApiResponse<?>updateCourse(UpdateCourseReq updateCourseReq);
    ApiResponse<?>deleteCourse( Integer Id);
    ApiResponse<?>listAllCourses(PaginationDto paginationDto);
    ApiResponse<?>listFreeCourses(PaginationDto paginationDto);
    ApiResponse<?>listPaidCourses(PaginationDto paginationDto);
    ApiResponse<?>viewCourse(ViewCourseRequest viewCourseRequest );


}
