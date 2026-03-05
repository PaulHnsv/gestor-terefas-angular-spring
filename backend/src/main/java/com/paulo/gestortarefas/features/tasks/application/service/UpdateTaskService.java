package com.paulo.gestortarefas.features.tasks.application.service;

import com.paulo.gestortarefas.features.tasks.application.dto.TaskMapper;
import com.paulo.gestortarefas.features.tasks.application.dto.TaskRequest;
import com.paulo.gestortarefas.features.tasks.application.dto.TaskResponse;
import com.paulo.gestortarefas.features.tasks.domain.model.Task;
import com.paulo.gestortarefas.features.tasks.domain.ports.inbound.UpdateTaskUseCase;
import com.paulo.gestortarefas.features.tasks.domain.ports.outbound.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateTaskService implements UpdateTaskUseCase {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public TaskResponse update(Long id, TaskRequest request) {
        Task entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tarefa não encontrada"
                ));
        BeanUtils.copyProperties(request, entity);
        Task saved = repository.save(entity);
        return taskMapper.toResponse(saved);
    }
}
