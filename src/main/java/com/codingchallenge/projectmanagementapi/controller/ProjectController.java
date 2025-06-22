package com.codingchallenge.projectmanagementapi.controller;


import com.codingchallenge.projectmanagementapi.model.Project;
import com.codingchallenge.projectmanagementapi.repository.ProjectRepository;
import com.codingchallenge.projectmanagementapi.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/projects")
public class ProjectController {
    @Autowired
    private ProjectService service;

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


}
