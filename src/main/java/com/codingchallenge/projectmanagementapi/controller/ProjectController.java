package com.codingchallenge.projectmanagementapi.controller;


import com.codingchallenge.projectmanagementapi.dto.ProjectWithTaskIdsDTO;
import com.codingchallenge.projectmanagementapi.model.Project;
import com.codingchallenge.projectmanagementapi.model.Task;
import com.codingchallenge.projectmanagementapi.service.ProjectService;
import com.codingchallenge.projectmanagementapi.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/projects")
public class ProjectController {
    @Autowired
    private ProjectService service;
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<ProjectWithTaskIdsDTO>> getAllProjects() {
        return service.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Project project = service.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody @Valid Project project) {
        Project created = service.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> update(@PathVariable Long id, @RequestBody @Valid Project updatedProject) {
        Project updated = service.updateProject(id, updatedProject);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteProjectById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/with-tasks")
    public ResponseEntity<List<Project>> getAllProjectsWithTasks() {
        return ResponseEntity.ok(service.getAllProjectsWithTasks());
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<Task>> getTasksByProjectId(@PathVariable Long id) {

            List<Task> tasks = taskService.getTasksByProjectId(id);
            return ResponseEntity.ok(tasks);

    }
}
