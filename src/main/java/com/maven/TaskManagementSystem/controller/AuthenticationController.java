package com.maven.TaskManagementSystem.controller;

import com.maven.TaskManagementSystem.dao.request.SignInRequest;
import com.maven.TaskManagementSystem.dao.request.SignUpRequest;
import com.maven.TaskManagementSystem.dao.response.JwtAuthenticationResponse;
import com.maven.TaskManagementSystem.jwt.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/md")
public class AuthenticationController {
        private final AuthService authService;
        @PostMapping("/signup")
        public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
            return ResponseEntity.ok(authService.signUp(request));
        }

        @PostMapping("/signin")
        public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
            return ResponseEntity.ok(authService.signIn(request));
        }
}
