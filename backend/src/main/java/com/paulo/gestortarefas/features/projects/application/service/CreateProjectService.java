package com.paulo.gestortarefas.features.projects.application.service;

import com.paulo.gestortarefas.features.projects.application.dto.ProjectMapper;
import com.paulo.gestortarefas.features.projects.application.dto.ProjectResponse;
import com.paulo.gestortarefas.features.projects.domain.model.Project;
import com.paulo.gestortarefas.features.projects.domain.ports.inbound.CreateProjectUseCase;
import com.paulo.gestortarefas.features.projects.domain.ports.outbound.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProjectService implements CreateProjectUseCase {

    @Autowired
    private ProjectRepository repository;

    @Override
    public ProjectResponse create(String name) {
        Project project = new Project(null, name); // valida name no domínio
        Project saved = repository.save(project);
        return ProjectMapper.toResponse(saved);
    }
}
