package com.maven.TaskManagementSystem.mapper;

import com.maven.TaskManagementSystem.dto.UserDTO;
import com.maven.TaskManagementSystem.model.User;
import com.maven.TaskManagementSystem.service.TaskService;
import lombok.Data;

@Data
public class UserMapper {
    private static TaskService taskService;

    public static UserDTO toDto(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        return user;
    }
}
