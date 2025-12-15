package com.sudip.Student.Enrollment.System.service.impl;

import com.sudip.Student.Enrollment.System.Exception.DuplicateException;
import com.sudip.Student.Enrollment.System.Exception.NotFoundException;
import com.sudip.Student.Enrollment.System.core.dto.ApiResponse;
import com.sudip.Student.Enrollment.System.core.dto.PaginationDto;
import com.sudip.Student.Enrollment.System.core.service.EmailTemplateService;
import com.sudip.Student.Enrollment.System.dto.userdto.ListUserResponse;
import com.sudip.Student.Enrollment.System.dto.userdto.RegisterUsersReqDto;
import com.sudip.Student.Enrollment.System.dto.userdto.UpdateUserReq;
import com.sudip.Student.Enrollment.System.entity.User;
import com.sudip.Student.Enrollment.System.mapper.UserMapper;
import com.sudip.Student.Enrollment.System.repository.UserRepository;
import com.sudip.Student.Enrollment.System.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
  private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

   @Autowired
   private UserRepository userRepository;
   @Autowired
   private UserMapper userMapper;
    @Autowired
    private EmailTemplateService emailTemplateService;
    @Transactional
    @Override
    @CacheEvict(value ="users",allEntries = true)
    public ApiResponse<?> saveUsers(RegisterUsersReqDto registerUsersReqDto) {
       if(userRepository.existsByEmail(registerUsersReqDto.getEmail())) {
           log.error("Failed to save student.Student with email {} already exists ", registerUsersReqDto.getEmail());
           throw new DuplicateException("Student with email " + registerUsersReqDto.getEmail() + " already exists");
       }
       User user = userMapper.createUsers(registerUsersReqDto);
        emailTemplateService.sendMail(user);
       userRepository.save(user);
       log.info("user with email {} saved successfully", registerUsersReqDto.getEmail());
       return new ApiResponse<>(true,"user saved successfully" ,201, LocalDateTime.now());


    }

    @Override
    public ApiResponse<?> listUsers(PaginationDto paginationDto) {
        Pageable pageable = PageRequest.of(paginationDto.getPage(), paginationDto.getSize(), Sort.by(Sort.Direction.DESC,"id"));
        Page<User> userPage;

        if(paginationDto.getKeyword()!=null && !paginationDto.getKeyword().trim().isEmpty()) {
            userPage= userRepository.searchUser(paginationDto.getKeyword().trim(),pageable);

        }
        else{
            userPage= userRepository.findAll(pageable);
        }
        List<ListUserResponse> listUserResponse = userMapper.ListAllUsers(userPage);

        return new ApiResponse<>(true, "user listed successfully", 200, LocalDateTime.now(),listUserResponse);

    }
    @Override
    public ApiResponse<?> getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        return new ApiResponse<>(true, "user found", 200, LocalDateTime.now(), user.get());


    }



    @Override
    public ApiResponse<?> updateUsers(UpdateUserReq  updateUserReq) {
        Optional<User> user = userRepository.findById(updateUserReq.getId());
        boolean existsByEmail = userRepository.existsByEmail(updateUserReq.getEmail());
        if(existsByEmail) {
            throw new NotFoundException("User with email " + updateUserReq.getEmail() + " not found");

        }
        boolean existsByUsername = userRepository.existsByUsername(updateUserReq.getUsername());
        if(existsByUsername) {
            throw new NotFoundException("User with name " + updateUserReq.getUsername() + " not found");
        }
        User existingUser = user.get();
        User userToUpdate = userMapper.updateUsers(existingUser,updateUserReq);
        userRepository.save(userToUpdate);
        log.info("user with email {} updated successfully", updateUserReq.getEmail());
        return new ApiResponse<>(true, "user updated successfully", 200, LocalDateTime.now());
    }

    @Override
    public ApiResponse<?> deleteUsers(Integer id) {
        // use soft delete
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()) {
            throw new NotFoundException("User with id " + id + " not found");

        }
        User userToDelete = userMapper.deleteUsers(user.get());
        userRepository.save(userToDelete);
        log.info("user with id {} deleted successfully", id);
        return new ApiResponse<>(true, "user deleted successfully", 200, LocalDateTime.now());
    }


}
