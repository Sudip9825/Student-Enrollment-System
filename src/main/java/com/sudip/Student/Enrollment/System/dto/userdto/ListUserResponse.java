package com.sudip.Student.Enrollment.System.dto.userdto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
public class ListUserResponse {
    private String username;
    private String email;
    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:s a")
    private LocalDateTime createdAt ;
}
