package com.codingchallenge.projectmanagementapi.repository;

import com.codingchallenge.projectmanagementapi.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
