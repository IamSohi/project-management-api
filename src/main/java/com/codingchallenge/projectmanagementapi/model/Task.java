package com.codingchallenge.projectmanagementapi.model;

import com.codingchallenge.projectmanagementapi.model.enums.TaskPriority;
import com.codingchallenge.projectmanagementapi.model.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @NotBlank
    private String title;

    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
