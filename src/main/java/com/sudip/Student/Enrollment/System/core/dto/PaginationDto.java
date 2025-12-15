package com.sudip.Student.Enrollment.System.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description="pagination Object")
public class PaginationDto {
    @NotNull(message = "should not be  null") @Min(value = 0,message = "must start form zero")
    private Integer page;

    @NotNull(message = "should not be  null") @Min(value = 1, message = "Size must be at least 1")
    private Integer size;


    private String keyword;
}
