package com.sudip.Student.Enrollment.System.dto.coursedto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
public class ListCourseResponse {
    private Integer id;
    private String title;
    private String description;
    private String category;
    private String price;
    private Boolean isFree;
    @CreatedDate
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:s a")
    private LocalDateTime created_at;

}
