package com.paulo.gestortarefas.features.tasks.domain.ports.inbound;

import com.paulo.gestortarefas.features.tasks.application.dto.TaskResponse;

import java.util.List;

public interface ListTasksUseCase {
    List<TaskResponse> list();
    TaskResponse findById(Long id);
    List<TaskResponse> findByProjectId(Long id);
}
