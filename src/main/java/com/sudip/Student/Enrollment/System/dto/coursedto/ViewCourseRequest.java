package com.sudip.Student.Enrollment.System.dto.coursedto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewCourseRequest {
    @NotNull(message = "Id is required")
    private Integer courseId;
}
