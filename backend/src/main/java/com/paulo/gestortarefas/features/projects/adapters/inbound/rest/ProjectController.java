package com.paulo.gestortarefas.features.projects.adapters.inbound.rest;

import com.paulo.gestortarefas.features.projects.adapters.outbound.persistence.ProjectJpaEntity;
import com.paulo.gestortarefas.features.projects.application.dto.ProjectRequest;
import com.paulo.gestortarefas.features.projects.application.dto.ProjectResponse;
import com.paulo.gestortarefas.features.projects.domain.ports.inbound.CreateProjectUseCase;
import com.paulo.gestortarefas.features.projects.domain.ports.inbound.DeleteProjectUseCase;
import com.paulo.gestortarefas.features.projects.domain.ports.inbound.ListProjectsUseCase;
import com.paulo.gestortarefas.features.projects.domain.ports.inbound.UpdateProjectUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private CreateProjectUseCase createUseCase;
    @Autowired
    private ListProjectsUseCase listUseCase;
    @Autowired
    private UpdateProjectUseCase updateProjectUseCase;
    @Autowired
    private DeleteProjectUseCase deleteProjectUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse create(@Valid @RequestBody ProjectRequest request) {
        return createUseCase.create(request);
    }

    @GetMapping
    public List<ProjectResponse> list() {
        return listUseCase.list();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectResponse update(@Valid @RequestBody ProjectRequest request, @PathVariable Long id){
        return updateProjectUseCase.update(id, request);
    }

    @GetMapping("/{id}")
    public ProjectResponse findById(@PathVariable Long id){
        return listUseCase.findById(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
    }
}