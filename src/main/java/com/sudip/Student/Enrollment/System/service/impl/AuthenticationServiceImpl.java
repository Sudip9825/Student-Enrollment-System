package com.sudip.Student.Enrollment.System.service.impl;

import com.sudip.Student.Enrollment.System.Exception.InvalidTokenException;
import com.sudip.Student.Enrollment.System.Exception.NotFoundException;
import com.sudip.Student.Enrollment.System.core.dto.ApiResponse;
import com.sudip.Student.Enrollment.System.core.dto.LoginRequest;
import com.sudip.Student.Enrollment.System.core.security.JwtFilter;
import com.sudip.Student.Enrollment.System.core.security.JwtService;
import com.sudip.Student.Enrollment.System.dto.userdto.AuthenticationResponse;
import com.sudip.Student.Enrollment.System.entity.User;
import com.sudip.Student.Enrollment.System.repository.UserRepository;
import com.sudip.Student.Enrollment.System.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private UserRepository userRepository;

    @Override
    public ApiResponse<?> authenticate(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken( loginRequest.getEmail(),loginRequest.getPassword())
            );
            String accessToken = jwtService.generateAccessToken(user.get());
            String refreshToken = jwtService.generateRefreshToken(user.get());
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setAccessToken(accessToken);
            authenticationResponse.setRefreshToken(refreshToken);
            return new ApiResponse<>(true,"logged in successfully",200,authenticationResponse);
        }
        catch(BadCredentialsException bce){
            throw new BadCredentialsException("Invalid email or password");
        }
    }

    @Override
    public ApiResponse<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader == null && !authHeader.startsWith("Bearer ")){
            throw new InvalidTokenException("Invalid token");

        }
        String refreshTokenInHeader = authHeader.substring(7);
        Optional<User> user =  userRepository.findByEmail(jwtService.extractEmail(refreshTokenInHeader));
        if(user.isEmpty()){
            throw new NotFoundException("User not found");

        }
        boolean isRefreshTokenValid = jwtService.validateRefreshToken(refreshTokenInHeader,user.get().getEmail());
        if(isRefreshTokenValid){
            String accessToken = jwtService
                    .generateAccessToken(user.get());
            String refreshToken = jwtService.generateRefreshToken(user.get());
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setAccessToken(accessToken);
            authenticationResponse.setRefreshToken(refreshToken);
            return new ApiResponse<>(true,"Token refreshed successfully",200,authenticationResponse);

        }
        throw new InvalidTokenException("Invalid token");
    }
}
