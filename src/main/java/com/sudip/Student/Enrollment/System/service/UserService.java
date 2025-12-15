package com.sudip.Student.Enrollment.System.service;

import com.sudip.Student.Enrollment.System.core.dto.ApiResponse;
import com.sudip.Student.Enrollment.System.core.dto.PaginationDto;
import com.sudip.Student.Enrollment.System.dto.userdto.RegisterUsersReqDto;
import com.sudip.Student.Enrollment.System.dto.userdto.UpdateUserReq;

public interface UserService {
    ApiResponse<?>saveUsers(RegisterUsersReqDto registerUsersReqDto);
    ApiResponse<?>listUsers(PaginationDto paginationDto);
    ApiResponse<?>deleteUsers(Integer id);
    ApiResponse<?>updateUsers(UpdateUserReq updateUserReq);
    ApiResponse<?>getUserById(Integer id);
}
