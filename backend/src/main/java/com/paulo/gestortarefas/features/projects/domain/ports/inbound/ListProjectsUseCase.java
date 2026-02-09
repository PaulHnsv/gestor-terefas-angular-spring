package com.paulo.gestortarefas.features.projects.domain.ports.inbound;

import com.paulo.gestortarefas.features.projects.application.dto.ProjectResponse;

import java.util.List;

public interface ListProjectsUseCase {
    List<ProjectResponse> list();
    ProjectResponse findById(Long id);
}
