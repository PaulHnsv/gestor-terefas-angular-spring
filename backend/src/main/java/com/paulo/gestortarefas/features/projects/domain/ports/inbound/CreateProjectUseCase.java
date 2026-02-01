package com.paulo.gestortarefas.features.projects.domain.ports.inbound;

import com.paulo.gestortarefas.features.projects.application.dto.ProjectResponse;

public interface CreateProjectUseCase {
    ProjectResponse create(String name, String description);
}
