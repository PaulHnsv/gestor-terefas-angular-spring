package com.paulo.gestortarefas.features.projects.domain.ports.inbound;

import com.paulo.gestortarefas.features.projects.application.dto.ProjectRequest;
import com.paulo.gestortarefas.features.projects.application.dto.ProjectResponse;

public interface UpdateProjectUseCase {
    ProjectResponse update(Long id, ProjectRequest request);
}
