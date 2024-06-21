package com.maven.TaskManagementSystem.mapper;

import com.maven.TaskManagementSystem.dto.GetAllTasksByUserDTO;
import com.maven.TaskManagementSystem.dto.TaskDTO;
import com.maven.TaskManagementSystem.model.Task;
import com.maven.TaskManagementSystem.model.User;

import java.util.List;

public class TaskMapper {

    public static TaskDTO toDto(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskId(task.getId());
        taskDTO.setTaskTitle(task.getTitle());
        taskDTO.setTaskDescription(task.getDescription());
        taskDTO.setTaskDueDate(task.getDueDate());
        taskDTO.setTaskStatus(task.getStatus());
        taskDTO.setUserDTO(UserMapper.toDto(task.getUser()));
        return taskDTO;
    }

    public static Task toEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getTaskId());
        task.setTitle(taskDTO.getTaskTitle());
        task.setDescription(taskDTO.getTaskDescription());
        task.setDueDate(taskDTO.getTaskDueDate());
        task.setStatus(taskDTO.getTaskStatus());
        task.setUser(UserMapper.toEntity(taskDTO.getUserDTO()));
        return task;
    }

    public static GetAllTasksByUserDTO map(List<Task> taskList) {
        if (taskList.isEmpty()) {
            return null;
        }
        User user = taskList.get(0).getUser();
        GetAllTasksByUserDTO getAllTasksByUserDTO = new GetAllTasksByUserDTO();
        getAllTasksByUserDTO.setUserId(user.getId());
        getAllTasksByUserDTO.setUserName(user.getUsername());
        getAllTasksByUserDTO.setUserPassword(user.getPassword());
        List<GetAllTasksByUserDTO.Task> userTasks = taskList.stream().map(task -> {
            GetAllTasksByUserDTO.Task getUserTasks = new GetAllTasksByUserDTO.Task();
            getUserTasks.setTaskId(task.getId());
            getUserTasks.setTaskTitle(task.getTitle());
            getUserTasks.setTaskDescription(task.getDescription());
            getUserTasks.setTaskDueDate(task.getDueDate());
            getUserTasks.setTaskStatus(task.getStatus());
            return getUserTasks;
        }).toList();
        getAllTasksByUserDTO.setTasks(userTasks);
        return getAllTasksByUserDTO;
    }
}
