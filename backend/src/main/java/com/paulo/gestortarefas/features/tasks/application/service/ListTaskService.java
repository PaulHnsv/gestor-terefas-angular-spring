package com.paulo.gestortarefas.features.tasks.application.service;

import com.paulo.gestortarefas.features.projects.domain.model.Project;
import com.paulo.gestortarefas.features.tasks.application.dto.TaskMapper;
import com.paulo.gestortarefas.features.tasks.application.dto.TaskResponse;
import com.paulo.gestortarefas.features.tasks.domain.model.Task;
import com.paulo.gestortarefas.features.tasks.domain.ports.inbound.ListTasksUseCase;
import com.paulo.gestortarefas.features.tasks.domain.ports.outbound.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class ListTaskService implements ListTasksUseCase {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<TaskResponse> list() {
        return taskMapper.toResponseList(repository.findAll());
    }

    @Override
    public TaskResponse findById(Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tarefa não encontrada"
                ));
        return taskMapper.toResponse(task);
    }

    @Override
    public TaskResponse findByProjectId(Long id) {
        Task task = repository.findByProjectId(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tarefa não encontrada"
        ));;
        return taskMapper.toResponse(task);
    }
}
