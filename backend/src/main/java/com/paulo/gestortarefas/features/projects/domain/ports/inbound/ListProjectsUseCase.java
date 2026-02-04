package com.paulo.gestortarefas.features.projects.domain.ports.inbound;

import com.paulo.gestortarefas.features.projects.adapters.outbound.persistence.ProjectJpaEntity;
import com.paulo.gestortarefas.features.projects.application.dto.ProjectResponse;

import java.util.List;
import java.util.Optional;

public interface ListProjectsUseCase {
    List<ProjectResponse> list();
    ProjectResponse findById(Long id);
}
