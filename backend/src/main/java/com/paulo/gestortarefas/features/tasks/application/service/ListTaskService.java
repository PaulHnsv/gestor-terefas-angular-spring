package com.paulo.gestortarefas.features.tasks.application.service;

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
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


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
    public List<TaskResponse> findByProjectId(Long id) {
        List<Task> tasks = repository.findByProjectId(id).stream()
                .flatMap(Optional::stream) // Java 9+
                .toList();

        if (tasks.isEmpty())
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tarefas não encontradas");

        return taskMapper.toResponseList(tasks);

    }
}
