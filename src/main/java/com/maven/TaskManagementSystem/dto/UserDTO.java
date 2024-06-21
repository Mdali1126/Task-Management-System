package com.maven.TaskManagementSystem.dto;

import com.maven.TaskManagementSystem.model.Role;
import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String username;
    private String Password;
    private Role role;
}
