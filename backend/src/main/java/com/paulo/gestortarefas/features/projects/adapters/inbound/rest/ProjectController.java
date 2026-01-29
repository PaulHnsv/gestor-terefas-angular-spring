package com.paulo.gestortarefas.features.projects.adapters.inbound.rest;

import com.paulo.gestortarefas.features.projects.application.dto.ProjectRequest;
import com.paulo.gestortarefas.features.projects.application.dto.ProjectResponse;
import com.paulo.gestortarefas.features.projects.domain.ports.inbound.CreateProjectUseCase;
import com.paulo.gestortarefas.features.projects.domain.ports.inbound.ListProjectsUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final CreateProjectUseCase createUseCase;
    private final ListProjectsUseCase listUseCase;

    public ProjectController(CreateProjectUseCase createUseCase,
                             ListProjectsUseCase listUseCase) {
        this.createUseCase = createUseCase;
        this.listUseCase = listUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse create(@Valid @RequestBody ProjectRequest request) {
        return createUseCase.create(request.getName());
    }

    @GetMapping
    public List<ProjectResponse> list() {
        return listUseCase.list();
    }
}