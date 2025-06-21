package com.codingchallenge.projectmanagementapi.service;


import com.codingchallenge.projectmanagementapi.model.Project;
import com.codingchallenge.projectmanagementapi.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}
