package com.maven.TaskManagementSystem.dao.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignInRequest {
    private String username;
    private String password;
    private String role;
}
