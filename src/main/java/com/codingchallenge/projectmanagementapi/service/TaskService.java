package com.codingchallenge.projectmanagementapi.service;

import com.codingchallenge.projectmanagementapi.dto.TaskWithProjectDTO;
import com.codingchallenge.projectmanagementapi.exception.ResourceNotFoundException;
import com.codingchallenge.projectmanagementapi.model.Project;
import com.codingchallenge.projectmanagementapi.model.Task;
import com.codingchallenge.projectmanagementapi.repository.ProjectRepository;
import com.codingchallenge.projectmanagementapi.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository){
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }



    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id){
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with ID " + id + " not found"));
    }

    public Task createTask(Task task){
        Long projectId = task.getProject().getId();
        Project project = projectRepository.findById(projectId)
                .orElseThrow(()-> new ResourceNotFoundException("Project with id " + projectId + " not found."));
        task.setProject(project);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask){
        Task existingTask = getTaskById(id);

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setPriority(updatedTask.getPriority());
        existingTask.setUpdatedAt(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public List<Task> getTasksByProjectId(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("Project with id " + projectId + " not found.");
        }
        return taskRepository.findByProjectId(projectId);
    }

    public List<TaskWithProjectDTO> getAllTasksWithProjects() {
        List<Task> tasks = taskRepository.findAllWithProject(); // @Query with JOIN FETCH
        return tasks.stream()
                .map(TaskWithProjectDTO::new)
                .collect(Collectors.toList());
    }
}
