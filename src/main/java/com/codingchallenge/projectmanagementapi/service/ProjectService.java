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

    public ResponseEntity<List<ProjectWithTaskIdsDTO>> getAllProjects() {

        List<Project> projects = projectRepository.findAll();
        List<ProjectWithTaskIdsDTO> dtoList = projects.stream()
                .map(ProjectWithTaskIdsDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project with ID " + id + " not found"));
    }

    public Project createProject(Project project) {
        project.setCreatedAt(LocalDateTime.now());
        project.setUpdatedAt(LocalDateTime.now());
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project updatedProject) {
        Project existingProject = getProjectById(id);
        existingProject.setName(updatedProject.getName());
        existingProject.setDescription(updatedProject.getDescription());
        existingProject.setStatus(updatedProject.getStatus());
        existingProject.setUpdatedAt(LocalDateTime.now());
        return projectRepository.save(existingProject);
    }

    public void deleteProjectById(Long id) {
        Project existing = getProjectById(id);
        projectRepository.delete(existing);
    }


    public List<Project> getAllProjectsWithTasks() {
        return projectRepository.findAllWithTasks(); // custom @Query with JOIN FETCH
    }
}
