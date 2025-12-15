package com.sudip.Student.Enrollment.System.controller;

import com.sudip.Student.Enrollment.System.core.dto.ApiResponse;
import com.sudip.Student.Enrollment.System.core.dto.PaginationDto;
import com.sudip.Student.Enrollment.System.dto.coursedto.AddCourseReq;
import com.sudip.Student.Enrollment.System.dto.coursedto.UpdateCourseReq;
import com.sudip.Student.Enrollment.System.dto.coursedto.ViewCourseRequest;
import com.sudip.Student.Enrollment.System.dto.coursedto.ViewCourseResponse;
import com.sudip.Student.Enrollment.System.entity.Course;
import com.sudip.Student.Enrollment.System.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<?>> savecoure(@RequestBody @Valid AddCourseReq addCourseReq) {
        ApiResponse<?> apiresponse = courseService.addCourse(addCourseReq);
        return ResponseEntity.ok(apiresponse);
    }
    @PostMapping("/list")
    public ResponseEntity<ApiResponse<?>> getCourses(@RequestBody @Valid PaginationDto paginationDto) {
        ApiResponse<?> apiResponse = courseService.listAllCourses(paginationDto);
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("/view")
    public ResponseEntity<ApiResponse<?>> viewCourse(@RequestBody @Valid ViewCourseRequest viewCourseRequest) {
        ApiResponse<?> apiResponse = courseService.viewCourse(viewCourseRequest);
        return ResponseEntity.ok(apiResponse);

    }
    @PostMapping("/update")
    public ResponseEntity<ApiResponse<?>> updateCourse(@RequestBody @Valid UpdateCourseReq updateCourseReq) {
        ApiResponse<?> apiResponse = courseService.updateCourse(updateCourseReq);

    return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("/paidCourses")
    public ResponseEntity<ApiResponse<?>> paidCourses(@RequestBody @Valid PaginationDto paginationDto) {
        ApiResponse<?> apiResponse = courseService.listPaidCourses(paginationDto);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/freeCourses")
    public ResponseEntity<ApiResponse<?>> freeCourses(@RequestBody @Valid PaginationDto paginationDto) {
        ApiResponse<?> apiResponse = courseService.listFreeCourses(paginationDto);
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("/delete{id}")
    public ResponseEntity<ApiResponse<?>> deleteByid(@PathVariable Integer id) {
        ApiResponse<?> apiResponse = courseService.deleteCourse(id);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}


