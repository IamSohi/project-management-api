package com.codingchallenge.projectmanagementapi.service;


import com.codingchallenge.projectmanagementapi.model.Project;
import com.codingchallenge.projectmanagementapi.model.Task;
import com.codingchallenge.projectmanagementapi.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
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

    public boolean existsById(Long id) {
        return projectRepository.existsById(id);
    }

    public List<Project> getAllProjectsWithTasks() {
        return projectRepository.findAllWithTasks(); // custom @Query with JOIN FETCH
    }
//    public ResponseEntity<List<Task>> getTasksByProjectId(Long id) {
//        if (!projectRepository.existsById(id)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//        List<Task> tasks = taskRepository.findByProjectId(id);
//        return ResponseEntity.ok(tasks);
//
//    }
}
