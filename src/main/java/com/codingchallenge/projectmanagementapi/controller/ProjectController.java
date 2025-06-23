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
        return ResponseEntity.ok(service.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectWithTaskIdsDTO>  getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProjectById(id));
    }

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody @Valid Project project) {
        Project created = service.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectWithTaskIdsDTO> update(@PathVariable Long id, @RequestBody @Valid Project updatedProject) {
        return ResponseEntity.ok(service.updateProject(id, updatedProject));
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
