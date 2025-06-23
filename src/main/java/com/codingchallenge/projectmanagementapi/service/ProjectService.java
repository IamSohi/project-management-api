package com.codingchallenge.projectmanagementapi.service;


import com.codingchallenge.projectmanagementapi.dto.ProjectWithTaskIdsDTO;
import com.codingchallenge.projectmanagementapi.exception.ResourceNotFoundException;
import com.codingchallenge.projectmanagementapi.model.Project;
import com.codingchallenge.projectmanagementapi.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectWithTaskIdsDTO> getAllProjects() {

        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(ProjectWithTaskIdsDTO::new)
                .collect(Collectors.toList());
    }

    public ProjectWithTaskIdsDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project with ID " + id + " not found"));

        return new ProjectWithTaskIdsDTO(project);
    }

    private Project findProjectEntityById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project with ID " + id + " not found"));
    }

    public Project createProject(Project project) {
        project.setCreatedAt(LocalDateTime.now());
        project.setUpdatedAt(LocalDateTime.now());
        return projectRepository.save(project);
    }

    public ProjectWithTaskIdsDTO updateProject(Long id, Project updatedProject) {
        Project existingProject = findProjectEntityById(id);
        existingProject.setName(updatedProject.getName());
        existingProject.setDescription(updatedProject.getDescription());
        existingProject.setStatus(updatedProject.getStatus());
        existingProject.setUpdatedAt(LocalDateTime.now());
        Project savedProject = projectRepository.save(existingProject);
        return new ProjectWithTaskIdsDTO(savedProject);
    }

    public void deleteProjectById(Long id) {
        Project existing = findProjectEntityById(id);
        projectRepository.delete(existing);
    }


    public List<Project> getAllProjectsWithTasks() {
        return projectRepository.findAllWithTasks(); // custom @Query with JOIN FETCH
    }
}
