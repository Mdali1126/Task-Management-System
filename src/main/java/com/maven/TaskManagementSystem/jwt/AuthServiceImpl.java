package com.maven.TaskManagementSystem.jwt;

import com.maven.TaskManagementSystem.dao.request.SignInRequest;
import com.maven.TaskManagementSystem.dao.request.SignUpRequest;
import com.maven.TaskManagementSystem.model.User;
import com.maven.TaskManagementSystem.model.Role;
import com.maven.TaskManagementSystem.dao.response.JwtAuthenticationResponse;
import com.maven.TaskManagementSystem.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Data
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
      User user = User.builder().username(request.getUsername())
               .password(passwordEncoder.encode(request.getPassword())).role(Role.valueOf(request.getRole())).build();
                userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
       User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
       String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
