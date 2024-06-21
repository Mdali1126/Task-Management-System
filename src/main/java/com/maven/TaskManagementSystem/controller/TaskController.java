package com.maven.TaskManagementSystem.controller;

import com.maven.TaskManagementSystem.dto.GetAllTasksByUserDTO;
import com.maven.TaskManagementSystem.dto.TaskDTO;
import com.maven.TaskManagementSystem.model.Task;
import com.maven.TaskManagementSystem.mapper.TaskMapper;
import com.maven.TaskManagementSystem.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("md/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO ){
        Task task = TaskMapper.toEntity(taskDTO);
        Task createdTask = taskService.createTask(task);
        TaskDTO createdTaskDTO = TaskMapper.toDto(createdTask);
        return new ResponseEntity<>(createdTaskDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id){
       Task task = taskService.getTaskById(id);
       TaskDTO taskDTO = TaskMapper.toDto(task);
       return ResponseEntity.ok(taskDTO);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(){
        List<Task> tasks = taskService.getAllTasks();
        List<TaskDTO> taskDTOS = tasks.stream().map(TaskMapper :: toDto).toList();
        return ResponseEntity.ok(taskDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO){
        taskDTO.setTaskId(id);
        Task task = TaskMapper.toEntity(taskDTO);
        Task updatedTask = taskService.updateTask(task);
        TaskDTO updatedTaskDTO = TaskMapper.toDto(updatedTask);
        return ResponseEntity.ok(updatedTaskDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Long id){
       taskService.deleteTask(id);
       return new ResponseEntity<>("Task Deleted Successfully",HttpStatus.OK);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<GetAllTasksByUserDTO> getTasksByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTasksByUserId(id));
    }
}
