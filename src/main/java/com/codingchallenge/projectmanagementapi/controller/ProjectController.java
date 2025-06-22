package com.codingchallenge.projectmanagementapi.controller;


import com.codingchallenge.projectmanagementapi.model.Project;
import com.codingchallenge.projectmanagementapi.model.Task;
import com.codingchallenge.projectmanagementapi.repository.ProjectRepository;
import com.codingchallenge.projectmanagementapi.service.ProjectService;
import com.codingchallenge.projectmanagementapi.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/projects")
public class ProjectController {
    @Autowired
    private ProjectService service;
    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Project> getAllProjects() { return service.getAllProjects(); }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) { return service.getProjectById(id); }

    @PostMapping
    public Project create(@RequestBody @Valid Project project) {
        return service.createProject(project);
    }

    @PutMapping("/{id}")
    public Project update(@PathVariable Long id, @RequestBody Project updatedProject) {
        return service.updateProject(id, updatedProject);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.deleteProjectById(id); }

    @GetMapping("/with-tasks")
    public ResponseEntity<List<Project>> getAllProjectsWithTasks() {
        return ResponseEntity.ok(service.getAllProjectsWithTasks());
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<Task>> getTasksByProjectId(@PathVariable Long id) {
//        try {
            List<Task> tasks = taskService.getTasksByProjectId(id);
            return ResponseEntity.ok(tasks);
//        } catch (ResourceNotFoundException ex) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
    }
}
