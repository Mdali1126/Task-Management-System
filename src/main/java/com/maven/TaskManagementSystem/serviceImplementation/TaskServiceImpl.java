package com.maven.TaskManagementSystem.serviceImplementation;

import com.maven.TaskManagementSystem.dto.GetAllTasksByUserDTO;
import com.maven.TaskManagementSystem.model.Task;
import com.maven.TaskManagementSystem.model.User;
import com.maven.TaskManagementSystem.exception.TaskNotFoundException;
import com.maven.TaskManagementSystem.exception.UserNotFoundException;
import com.maven.TaskManagementSystem.mapper.TaskMapper;
import com.maven.TaskManagementSystem.repository.TaskRepository;
import com.maven.TaskManagementSystem.repository.UserRepository;
import com.maven.TaskManagementSystem.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    @Override
    public Task createTask(Task task) {
        if (task.getUser() != null && task.getUser().getId() != 0) {
            User user = userRepository.findById(task.getUser().getId())
                    .orElseThrow(() -> new UserNotFoundException(task.getUser().getId()));
            task.setUser(user);
        } else {
            task.setUser(null);
        }
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task updateTask(Task task) {
        Task existingTask = taskRepository.findById(task.getId()).orElseThrow(() -> new TaskNotFoundException(task.getId()));
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setStatus(task.getStatus());
        if (task.getUser() != null && task.getUser().getId() != 0){
            User user = userRepository.findById(task.getUser().getId())
                    .orElseThrow(() -> new UserNotFoundException(task.getUser().getId()));
            existingTask.setUser(user);
        }
        else {
            existingTask.setUser(null);
        }
        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public GetAllTasksByUserDTO getTasksByUserId(Long userId) {
        return TaskMapper.map(taskRepository.findByUserId(userId));
    }
}
