package com.maven.TaskManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "YYYY-MM-DD")
    private LocalDate dueDate;
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assignedToUser_id",referencedColumnName = "id")
    private User user;
}
