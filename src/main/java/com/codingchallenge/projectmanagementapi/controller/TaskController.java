package com.codingchallenge.projectmanagementapi.controller;

import com.codingchallenge.projectmanagementapi.model.Task;
import com.codingchallenge.projectmanagementapi.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping
    public List<Task> getAllTasks(){
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return service.getTaskById(id);
    }

    @PostMapping
    public Task create(@RequestBody @Valid Task task){
        return service.createTask(task);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task updatedTask){
        return service.updateTask(id, updatedTask);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteTask(id);
    }


    @GetMapping("/with-projects")
    public ResponseEntity<List<Task>> getAllTasksWithProjects() {
        return ResponseEntity.ok(service.getAllTasksWithProjects());
    }

}
