package com.paulo.gestortarefas.features.tasks.application.service;

import com.paulo.gestortarefas.features.projects.domain.model.Project;
import com.paulo.gestortarefas.features.tasks.application.dto.TaskMapper;
import com.paulo.gestortarefas.features.tasks.application.dto.TaskRequest;
import com.paulo.gestortarefas.features.tasks.application.dto.TaskResponse;
import com.paulo.gestortarefas.features.tasks.domain.model.Task;
import com.paulo.gestortarefas.features.tasks.domain.ports.inbound.UpdateTaskUseCase;
import com.paulo.gestortarefas.features.tasks.domain.ports.outbound.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProjectService implements UpdateTaskUseCase {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public TaskResponse update(Long id, TaskRequest request) {
        Task entity = repository.findById(id);
        BeanUtils.copyProperties(request, entity);
        Task saved = repository.save(entity);
        return taskMapper.toResponse(saved);
    }
}
