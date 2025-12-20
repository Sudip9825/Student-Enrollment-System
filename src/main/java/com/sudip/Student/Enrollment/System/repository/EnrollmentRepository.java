package com.sudip.Student.Enrollment.System.repository;

import com.sudip.Student.Enrollment.System.entity.Course;
import com.sudip.Student.Enrollment.System.entity.Enrollment;
import com.sudip.Student.Enrollment.System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    boolean existsByUserAndCourse(User user, Optional<Course> course);





}
