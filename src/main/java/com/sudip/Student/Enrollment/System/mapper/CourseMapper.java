package com.sudip.Student.Enrollment.System.mapper;

import com.sudip.Student.Enrollment.System.dto.coursedto.AddCourseReq;
import com.sudip.Student.Enrollment.System.dto.coursedto.ListCourseResponse;
import com.sudip.Student.Enrollment.System.dto.coursedto.UpdateCourseReq;
import com.sudip.Student.Enrollment.System.dto.coursedto.ViewCourseResponse;
import com.sudip.Student.Enrollment.System.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CourseMapper {
    public Course createCourse(AddCourseReq addCourseReq) {
        Course course = new Course();
        course.setCourseTitle(addCourseReq.getCourseTitle());
        course.setDescription(addCourseReq.getDescription());
        course.setCategory(addCourseReq.getCategory());
        course.setPrice(Long.valueOf(addCourseReq.getPrice()));
        return course;
    }

    //mapper for listing all courses
    @Mapping(target = "title", source = "courseTitle")
    @Mapping(target = "isFree", source = "isFree")
    public abstract ListCourseResponse entityToResponseDto(Course course);
    public List<ListCourseResponse> ListAllCourses(Page<Course> courses) {
        return courses.getContent()
                .stream()
                .map(this::entityToResponseDto)
                .collect(Collectors.toList());

    }
    //mapper to view one course
    public abstract ViewCourseResponse entityToViewDetails(Course course);

    public Course updateCourse(Course course, UpdateCourseReq updateCourseReq) {
        course.setCourseTitle(updateCourseReq.getCourseTitle());
        course.setPrice(Long.valueOf(updateCourseReq.getPrice()));
        course.setCategory(updateCourseReq.getCategory());
        return course;

    }


}
