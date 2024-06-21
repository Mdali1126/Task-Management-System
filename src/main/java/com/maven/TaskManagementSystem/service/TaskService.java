package com.maven.TaskManagementSystem.service;

import com.maven.TaskManagementSystem.dto.GetAllTasksByUserDTO;
import com.maven.TaskManagementSystem.model.Task;

import java.util.List;

public interface TaskService {

    Task createTask(Task task);
    Task getTaskById(Long taskId);
    List<Task> getAllTasks();
    Task updateTask(Task task);
    void deleteTask(Long taskId);

    GetAllTasksByUserDTO getTasksByUserId(Long userId);

}

