package com.paulo.gestortarefas.features.tasks.application.dto;

import com.paulo.gestortarefas.features.tasks.adapters.outbound.persistence.TaskJpaEntity;
import com.paulo.gestortarefas.features.tasks.domain.model.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskResponse toResponse(Task task);

    List<TaskResponse> toResponseList(List<Task> tasks);

    TaskRequest toRequest(Task task);

    // Domínio → Entity (para persistir)
    TaskJpaEntity toEntity(Task task);

    // Entity → Domínio (para reconstituir)
    Task toDomain(TaskJpaEntity entity);
}
