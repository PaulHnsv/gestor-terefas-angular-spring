package com.paulo.gestortarefas.features.projects.application.dto;

import com.paulo.gestortarefas.features.projects.domain.model.Project;

public class ProjectMapper {
    public static ProjectResponse toResponse(Project project) {
        return new ProjectResponse(project.getId(), project.getName());
    }
}
