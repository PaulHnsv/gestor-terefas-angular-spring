package com.paulo.gestortarefas.features.tasks.domain.ports.inbound;

import com.paulo.gestortarefas.features.tasks.application.dto.TaskRequest;
import com.paulo.gestortarefas.features.tasks.application.dto.TaskResponse;

public interface CreateTaskUseCase {
    TaskResponse create(TaskRequest request);
}
