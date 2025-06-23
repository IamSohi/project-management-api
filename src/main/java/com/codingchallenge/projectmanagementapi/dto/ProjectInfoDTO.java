package com.codingchallenge.projectmanagementapi.dto;

import com.codingchallenge.projectmanagementapi.model.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInfoDTO {
    private Long id;
    private String name;
    private String status;

    public ProjectInfoDTO(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.status = project.getStatus().name();
    }
}
