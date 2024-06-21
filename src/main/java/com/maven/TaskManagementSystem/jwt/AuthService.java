package com.maven.TaskManagementSystem.jwt;

import com.maven.TaskManagementSystem.dao.request.SignInRequest;
import com.maven.TaskManagementSystem.dao.request.SignUpRequest;
import com.maven.TaskManagementSystem.dao.response.JwtAuthenticationResponse;

public interface AuthService {

    JwtAuthenticationResponse signUp(SignUpRequest request);
    JwtAuthenticationResponse signIn(SignInRequest request);
}
