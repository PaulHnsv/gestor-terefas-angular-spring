package com.paulo.gestortarefas.features.tasks.application.service;

import com.paulo.gestortarefas.features.tasks.application.dto.TaskMapper;
import com.paulo.gestortarefas.features.tasks.application.dto.TaskRequest;
import com.paulo.gestortarefas.features.tasks.application.dto.TaskResponse;
import com.paulo.gestortarefas.features.tasks.domain.model.Task;
import com.paulo.gestortarefas.features.tasks.domain.ports.inbound.CreateTaskUseCase;
import com.paulo.gestortarefas.features.tasks.domain.ports.outbound.TaskRepository;
import com.paulo.gestortarefas.shared.utils.Priority;
import com.paulo.gestortarefas.shared.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskService implements CreateTaskUseCase {

    @Autowired
    private TaskRepository repository;
    @Autowired
    private TaskMapper mapper;

    @Override
    public TaskResponse create(TaskRequest request) {
        Task task = new Task(
                request.getTitle(),
                Priority.fromCode(request.getPriority().getCode()),
                Status.fromCode(request.getStatus().getCode()),
                request.getDescription(),
                request.getProject()
        );
        Task saved = repository.save(task);
        return mapper.toResponse(saved);
    }


}
