package com.maven.TaskManagementSystem.dto;

import com.maven.TaskManagementSystem.model.Role;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class GetAllTasksByUserDTO {
    private long userId;
    private String userName;
    private String userPassword;
    private Role role;
    private List<Task> tasks;

    @Data
    public static class Task {
        private long taskId;
        private String taskTitle;
        private String taskDescription;
        private LocalDate taskDueDate;
        private String taskStatus;
    }

}
