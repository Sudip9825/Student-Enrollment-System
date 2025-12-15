package com.sudip.Student.Enrollment.System.dto.coursedto;

import lombok.Getter;
import lombok.Setter;

@Getter

@Setter
public class ViewCourseResponse {
    Integer course_id;
    String courseTitle;
    String description;
    String category;
    Long price;
}
