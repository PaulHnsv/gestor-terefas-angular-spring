package com.paulo.gestortarefas.features.projects.application.dto;

import com.paulo.gestortarefas.features.projects.adapters.outbound.persistence.ProjectJpaEntity;
import com.paulo.gestortarefas.features.projects.domain.model.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toResponse(Project project);

    List<ProjectResponse> toResponseList(List<Project> project);

    // Domínio → Entity (para persistir)
    ProjectJpaEntity toEntity(Project project);

    // Entity → Domínio (para reconstituir)
    Project toDomain(ProjectJpaEntity entity);
}
