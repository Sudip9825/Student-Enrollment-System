package com.sudip.Student.Enrollment.System.mapper;

import com.sudip.Student.Enrollment.System.dto.userdto.ListUserResponse;
import com.sudip.Student.Enrollment.System.dto.userdto.RegisterUsersReqDto;
import com.sudip.Student.Enrollment.System.dto.userdto.UpdateUserReq;
import com.sudip.Student.Enrollment.System.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel= MappingConstants.ComponentModel.SPRING)

public abstract class UserMapper {
    @Autowired
    protected PasswordEncoder passwordEncoder;



    public User createUsers(RegisterUsersReqDto registerUsersReqDto) {
        User user = new User();

        user.setEmail(registerUsersReqDto.getEmail());
        user.setUsername(registerUsersReqDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerUsersReqDto.getPassword()));
        user.setRole(registerUsersReqDto.getRole());
        return user;
    }

    public abstract ListUserResponse entityToResponseDto(User user);
    public List<ListUserResponse> ListAllUsers(Page<User> users) {
        return users.getContent()
                .stream()
                .map(this::entityToResponseDto)
                .collect(Collectors.toList());
    }
    public User updateUsers(User user ,UpdateUserReq updateUserReq) {
        user.setEmail(updateUserReq.getEmail());
        user.setUsername(updateUserReq.getUsername());
        return user;
    }

    public User deleteUsers(User user) {
        user.setDeleted(true);
        return user;
    }
}
