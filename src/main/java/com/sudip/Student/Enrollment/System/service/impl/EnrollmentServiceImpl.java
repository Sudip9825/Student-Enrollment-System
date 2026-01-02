package com.sudip.Student.Enrollment.System.service.impl;

import com.sudip.Student.Enrollment.System.Exception.NotFoundException;
import com.sudip.Student.Enrollment.System.core.dto.ApiResponse;
import com.sudip.Student.Enrollment.System.dto.Enrollmentdto.EnrollmentResponseDto;
import com.sudip.Student.Enrollment.System.entity.Course;
import com.sudip.Student.Enrollment.System.entity.Enrollment;
import com.sudip.Student.Enrollment.System.entity.User;
import com.sudip.Student.Enrollment.System.enums.EnrollmentStatus;
import com.sudip.Student.Enrollment.System.enums.PaymentStatus;
import com.sudip.Student.Enrollment.System.mapper.CourseMapper;
import com.sudip.Student.Enrollment.System.mapper.EnrollmentMapper;
import com.sudip.Student.Enrollment.System.repository.CourseRepo;
import com.sudip.Student.Enrollment.System.repository.EnrollmentRepository;
import com.sudip.Student.Enrollment.System.repository.UserRepository;
import com.sudip.Student.Enrollment.System.service.EnrollmentService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class EnrollmentServiceImpl implements EnrollmentService {

    private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private EnrollmentMapper enrollmentMapper;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private UserRepository userRepository;
    @Transactional


    @Override
    public ApiResponse<?> enrollCourse(Integer courseId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        User user =getLoggedInUser();
        boolean alreadyEnrolled =
                enrollmentRepository.existsByUserAndCourse(user, Optional.ofNullable(course));

        if (alreadyEnrolled) {
            throw new RuntimeException("User already enrolled in this course");
        }
        // 4️Create new enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDateTime.now());

        //  FREE vs PAID logic
        assert course != null;
        if (course.getPrice() == 0 ) {

            enrollment.setStatus(EnrollmentStatus.ACTIVE);
            enrollment.setPaymentStatus(PaymentStatus.FREE);
            enrollment.setAmount(BigDecimal.ZERO);

        } else {

            enrollment.setStatus(EnrollmentStatus.INACTIVE);
            enrollment.setPaymentStatus(PaymentStatus.PENDING);
            enrollment.setAmount(BigDecimal.valueOf(course.getPrice()));
        }

        // Save enrollment
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        //Convert entity to response DTO
        EnrollmentResponseDto responseDto =
                enrollmentMapper.toResponseDto(savedEnrollment);

        return new ApiResponse<>(true, "Enrollment successful", 200,LocalDateTime.now(), responseDto);
    }

    @Override
    public ApiResponse<?> getMyEnrollments() {
        User user = getLoggedInUser();
        List<Enrollment> enrollments = enrollmentRepository.findByUser(user);
        List<EnrollmentResponseDto> response = enrollments.stream()
                .map(enrollmentMapper::toResponseDto)
                .toList();
        return new ApiResponse<>(
                true, "My Enrollments", 200, LocalDateTime.now(), response);
    }

    @Override
    public ApiResponse<?> isUserEnrolled(Integer courseId) {
        User user = getLoggedInUser();

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        boolean isEnrolled = enrollmentRepository.existsByUserAndCourse(user, Optional.ofNullable(course));
         return new ApiResponse<>( true, "user enrolled",200,isEnrolled);

    }

    @Override
    public ApiResponse<?> getEnrollmentById(Integer enrollmentId) {
        return null;
    }

    @Override
    public ApiResponse<?> getMyEnrollmentsByCourse(Integer courseId) {
        return null;
    }

    private User getLoggedInUser() {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       UserDetails userDetails = (UserDetails) authentication.getPrincipal();
       String email = userDetails.getUsername();
       return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
