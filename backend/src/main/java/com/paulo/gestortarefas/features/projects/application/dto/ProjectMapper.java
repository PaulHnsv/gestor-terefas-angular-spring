package com.paulo.gestortarefas.features.projects.application.dto;

import com.paulo.gestortarefas.features.projects.adapters.outbound.persistence.ProjectJpaEntity;
import com.paulo.gestortarefas.features.projects.domain.model.Project;
import com.paulo.gestortarefas.features.tasks.adapters.outbound.persistence.TaskJpaEntity;
import com.paulo.gestortarefas.features.tasks.application.dto.TaskResponse;
import com.paulo.gestortarefas.features.tasks.domain.model.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toResponse(Project task);

    List<ProjectResponse> toResponseList(List<Project> tasks);

    // Domínio → Entity (para persistir)
    ProjectJpaEntity toEntity(Project task);

    // Entity → Domínio (para reconstituir)
    Project toDomain(ProjectJpaEntity entity);
}
