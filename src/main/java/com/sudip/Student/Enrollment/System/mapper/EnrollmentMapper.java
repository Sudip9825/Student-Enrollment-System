package com.sudip.Student.Enrollment.System.mapper;

import com.sudip.Student.Enrollment.System.dto.Enrollmentdto.EnrollmentResponseDto;
import com.sudip.Student.Enrollment.System.entity.Course;
import com.sudip.Student.Enrollment.System.entity.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class EnrollmentMapper {
    public EnrollmentResponseDto toResponseDto(Enrollment enrollment) {
        EnrollmentResponseDto dto = new EnrollmentResponseDto();

        dto.setEnrollmentId(enrollment.getId());
        dto.setEnrollmentDate(enrollment.getEnrollmentDate());
        dto.setStatus(enrollment.getStatus());
        dto.setPaymentStatus(enrollment.getPaymentStatus());
        dto.setAmount(enrollment.getAmount());

        // Course related data
        Course course = enrollment.getCourse();
        dto.setCourseId(course.getId());
        dto.setCourseTitle(course.getCourseTitle());

        return dto;
    }
}
