package com.codingchallenge.projectmanagementapi.controller;


import com.codingchallenge.projectmanagementapi.model.Project;
import com.codingchallenge.projectmanagementapi.repository.ProjectRepository;
import com.codingchallenge.projectmanagementapi.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/projects")
public class ProjectController {
    @Autowired
    private ProjectService service;

    @GetMapping
    public List<Project> getAllProjects() { return service.getAllProjects(); }


}
