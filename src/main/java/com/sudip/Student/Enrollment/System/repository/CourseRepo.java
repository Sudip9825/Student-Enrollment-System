package com.sudip.Student.Enrollment.System.repository;

import com.sudip.Student.Enrollment.System.entity.Course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course ,String> {

    Optional<Course> findById(Integer id);
    boolean existsBycourseTitle(String courseTitle);
    boolean existsByDescription(String course_description);



    Page<Course> findAll(Pageable pageable);


    @Query("""
                SELECT c FROM Course c
                WHERE (:keyword IS NULL OR LOWER(c.courseTitle) LIKE LOWER(CONCAT('%', :keyword, '%')))
            """)
    Page<Course> searchCourse(String keyword, Pageable pageable);

    @Query("""
        SELECT c FROM Course c
        WHERE c.price = 0 OR c.isFree = true
    """)
    Page<Course> findFreeCourses(Pageable pageable);


    @Query("""
        SELECT c FROM Course c
        WHERE c.price > 0 OR c.isFree = false
    """)
    Page<Course> findPaidCourses(Pageable pageable);


    boolean existsById(Integer courseId);


}
