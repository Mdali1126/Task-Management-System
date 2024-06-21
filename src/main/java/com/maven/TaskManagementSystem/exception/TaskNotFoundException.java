package com.maven.TaskManagementSystem.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Task Not Found " + id);
    }
}
