package com.paulo.gestortarefas.features.tasks.application.service;

import com.paulo.gestortarefas.features.projects.domain.model.Project;
import com.paulo.gestortarefas.features.tasks.application.dto.TaskMapper;
import com.paulo.gestortarefas.features.tasks.application.dto.TaskResponse;
import com.paulo.gestortarefas.features.tasks.domain.model.Task;
import com.paulo.gestortarefas.features.tasks.domain.ports.inbound.ListTasksUseCase;
import com.paulo.gestortarefas.features.tasks.domain.ports.outbound.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ListProjectsService implements ListTasksUseCase {

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
        Task task = repository.findById(id);
        return taskMapper.toResponse(task);
    }

}
