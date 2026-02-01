package com.paulo.gestortarefas.features.projects.adapters.inbound.rest;

import com.paulo.gestortarefas.features.projects.application.dto.ProjectRequest;
import com.paulo.gestortarefas.features.projects.application.dto.ProjectResponse;
import com.paulo.gestortarefas.features.projects.domain.ports.inbound.CreateProjectUseCase;
import com.paulo.gestortarefas.features.projects.domain.ports.inbound.ListProjectsUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private CreateProjectUseCase createUseCase;
    @Autowired
    private ListProjectsUseCase listUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse create(@Valid @RequestBody ProjectRequest request) {
        return createUseCase.create(request.getName(), request.getDescription());
    }

    @GetMapping
    public List<ProjectResponse> list() {
        return listUseCase.list();
    }
}