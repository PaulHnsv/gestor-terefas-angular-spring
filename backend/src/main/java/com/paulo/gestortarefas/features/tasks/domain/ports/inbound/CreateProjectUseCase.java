package com.paulo.gestortarefas.features.tasks.domain.ports.inbound;

import com.paulo.gestortarefas.features.projects.application.dto.ProjectRequest;
import com.paulo.gestortarefas.features.projects.application.dto.ProjectResponse;

public interface CreateProjectUseCase {
    ProjectResponse create(ProjectRequest request);
}
