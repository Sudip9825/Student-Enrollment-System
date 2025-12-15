package com.sudip.Student.Enrollment.System.dto.coursedto;

import lombok.Data;

@Data
public class UpdateCourseReq {
    private String id;
    private String courseTitle;
    private String price;
    private String category;
    private String description;
}
