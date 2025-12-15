package com.sudip.Student.Enrollment.System.controller;

import com.sudip.Student.Enrollment.System.core.dto.ApiResponse;
import com.sudip.Student.Enrollment.System.core.dto.PaginationDto;
import com.sudip.Student.Enrollment.System.dto.userdto.RegisterUsersReqDto;
import com.sudip.Student.Enrollment.System.dto.userdto.UpdateUserReq;
import com.sudip.Student.Enrollment.System.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<?>> saveusers(@RequestBody @Valid RegisterUsersReqDto registerUsersReqDto) {
        ApiResponse<?> apiresponse = userService.saveUsers(registerUsersReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiresponse);
    }
    @PostMapping("/list")
    public ResponseEntity<ApiResponse<?>> getusers(@RequestBody @Valid PaginationDto  paginationDto) {
        ApiResponse<?> apiResponse = userService.listUsers(paginationDto);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }
    @PostMapping("/listBy{id}")
    public ResponseEntity<ApiResponse<?>> getusersByid(@PathVariable Integer id) {
        ApiResponse<?> apiResponse = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PostMapping("/update")
   public ResponseEntity<ApiResponse<?>> updateuser(@RequestBody @Valid UpdateUserReq updateUserReq) {
        ApiResponse<?> apiResponse = userService.updateUsers(updateUserReq);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PostMapping("/delete{id}")
    public ResponseEntity<ApiResponse<?>> deleteByid(@PathVariable Integer id) {
        ApiResponse<?> apiResponse = userService.deleteUsers(id);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
