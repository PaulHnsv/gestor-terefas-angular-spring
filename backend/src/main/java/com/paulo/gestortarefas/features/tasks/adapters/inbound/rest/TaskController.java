package com.paulo.gestortarefas.features.tasks.adapters.inbound.rest;

import com.paulo.gestortarefas.features.projects.application.dto.ProjectRequest;
import com.paulo.gestortarefas.features.projects.application.dto.ProjectResponse;
import com.paulo.gestortarefas.features.tasks.application.dto.TaskRequest;
import com.paulo.gestortarefas.features.tasks.application.dto.TaskResponse;
import com.paulo.gestortarefas.features.tasks.domain.ports.inbound.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Tasks")
public class TaskController {

    @Autowired
    private CreateTaskUseCase create;

    @Autowired
    private ListTasksUseCase list;

    @Autowired
    private DeleteTaskUseCase delete;

    @Autowired
    private UpdateTaskUseCase update;

    @Autowired
    private ConcludeTaskUseCase concludeTaskUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse create(@Valid @RequestBody TaskRequest request) {
        return create.create(request);
    }

    @GetMapping
    public List<TaskResponse> list() {
        return list.list();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse update(@Valid @RequestBody TaskRequest request, @PathVariable Long id){
        return update.update(id, request);
    }

    @GetMapping("/{id}")
    public TaskResponse findById(@PathVariable Long id){
        return list.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        delete.delete(id);
    }

    @PatchMapping("/{id}")
    public void concludeTask(@PathVariable Long id){
        concludeTaskUseCase.concludeTask(id);
    }

}