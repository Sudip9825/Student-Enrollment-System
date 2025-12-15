package com.sudip.Student.Enrollment.System.service.impl;

import com.sudip.Student.Enrollment.System.Exception.DuplicateException;
import com.sudip.Student.Enrollment.System.Exception.NotFoundException;
import com.sudip.Student.Enrollment.System.core.dto.ApiResponse;
import com.sudip.Student.Enrollment.System.core.dto.PaginationDto;
import com.sudip.Student.Enrollment.System.dto.coursedto.*;
import com.sudip.Student.Enrollment.System.entity.Course;
import com.sudip.Student.Enrollment.System.mapper.CourseMapper;
import com.sudip.Student.Enrollment.System.repository.CourseRepo;
import com.sudip.Student.Enrollment.System.service.CourseService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private CourseMapper courseMapper;
    @Transactional
    @CacheEvict(value = "courses", allEntries = true )


    @Override
    public ApiResponse<?> addCourse(AddCourseReq addCourseReq) {
        if(courseRepo.existsBycourseTitle(addCourseReq.getCourseTitle())){
            logger.error("course title already exists");
            throw new DuplicateException("course title already exists");
        }
        Course course = courseMapper.createCourse(addCourseReq);
        courseRepo.save(course);
        logger.info("course saved successfully");
        return new ApiResponse<>(true,"course saved",201, LocalDateTime.now().toString());
    }

    @Override
    @Transactional
    public ApiResponse<?> updateCourse(UpdateCourseReq updateCourseReq) {
        Optional<Course> course = courseRepo.findById(updateCourseReq.getId());
        boolean existsByTitle = courseRepo.existsBycourseTitle(updateCourseReq.getCourseTitle());
        if(existsByTitle){
            throw new DuplicateException("course title already exists");
        }
        boolean existsByDescription = courseRepo.existsByDescription(updateCourseReq.getDescription());

        Course existingCourse = course.get();
        Course courseToUpdate = courseMapper.updateCourse(existingCourse,updateCourseReq);
        courseRepo.save(courseToUpdate);
        logger.info("course updated successfully");
        return new ApiResponse<>(true,"course updated",201, LocalDateTime.now());
    }

    @Override
    public ApiResponse<?> deleteCourse(Integer Id) {

        if(!courseRepo.existsById(Id)){
            throw new NotFoundException("course not found");
        }
        courseRepo.deleteById(String.valueOf(Id));

        logger.info("course deleted successfully");
        return new ApiResponse<>(true,"course deleted",200, LocalDateTime.now());
    }

    @Override
    public ApiResponse<?> listAllCourses(PaginationDto paginationDto) {
        Pageable pageable = PageRequest.of(paginationDto.getPage(), paginationDto.getSize(), Sort.by(Sort.Direction.DESC, "id"));
        Page<Course>  coursePage;

        if(paginationDto.getKeyword()!=null && !paginationDto.getKeyword().trim().isEmpty()){
            coursePage = courseRepo.searchCourse(paginationDto.getKeyword().trim(),pageable);

        }
        else{
            coursePage = courseRepo.findAll(pageable);
        }
        List<ListCourseResponse> listCourseResponse = courseMapper.ListAllCourses(coursePage);
        return new ApiResponse<>(true,"courses",200,listCourseResponse);
    }

    @Override
    public ApiResponse<?> listFreeCourses(PaginationDto paginationDto) {
        Pageable pageable = PageRequest.of(paginationDto.getPage(), paginationDto.getSize(), Sort.by(Sort.Direction.DESC, "id"));
        Page<Course> coursePage;
        coursePage = courseRepo.findFreeCourses(pageable);

        List<ListCourseResponse> listCourseResponse = courseMapper.ListAllCourses(coursePage);
        return new ApiResponse<>(true, "courses", 200, listCourseResponse);
    }


    @Override
    public ApiResponse<?> listPaidCourses(PaginationDto paginationDto){
        Pageable pageable = PageRequest.of(paginationDto.getPage(), paginationDto.getSize(), Sort.by(Sort.Direction.DESC, "id"));
        Page<Course>  coursePage;
            coursePage = courseRepo.findPaidCourses(pageable);

        List<ListCourseResponse> listCourseResponse = courseMapper.ListAllCourses(coursePage);
        return new ApiResponse<>(true,"courses",200,listCourseResponse);
    }

    @Override
    public  ApiResponse<?>viewCourse(ViewCourseRequest viewCourseRequest ){
        Optional<Course> course = courseRepo.findById(viewCourseRequest.getCourseId());
        if(course.isEmpty()){
            logger.error("course not found");
            throw new NotFoundException("course not found");
        }
        //map to view
        ViewCourseResponse viewCourseResponse = courseMapper.entityToViewDetails(course.get());

        logger.info("course  with id {} viewed successfully" , viewCourseRequest.getCourseId());

        return new ApiResponse<>(true,"course",200,course.get());
    }
}
