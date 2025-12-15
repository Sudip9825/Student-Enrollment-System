package com.sudip.Student.Enrollment.System.dto.coursedto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddCourseReq {
    @NotBlank(message = "course name  is needed")
    private String courseTitle;
    @NotBlank(message = "add description")
    private String description;

    private String category;

    private String price;


}
