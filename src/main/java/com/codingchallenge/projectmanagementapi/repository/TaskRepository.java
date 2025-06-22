package com.codingchallenge.projectmanagementapi.repository;

import com.codingchallenge.projectmanagementapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProjectId(Long projectId);

    @Query("SELECT t FROM Task t JOIN FETCH t.project")
    List<Task> findAllWithProject();

}

