package com.paulo.gestortarefas.features.projects.application.service;

import com.paulo.gestortarefas.features.projects.application.dto.ProjectMapper;
import com.paulo.gestortarefas.features.projects.application.dto.ProjectResponse;
import com.paulo.gestortarefas.features.projects.domain.model.Project;
import com.paulo.gestortarefas.features.projects.domain.ports.inbound.CreateProjectUseCase;
import com.paulo.gestortarefas.features.projects.domain.ports.outbound.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateProjectService implements CreateProjectUseCase {

    @Autowired
    private ProjectRepository repository;

    @Override
    public ProjectResponse create(String name, String description) {
        Project project = new Project(null, name, new Date(), description); // valida name no domínio
        Project saved = repository.save(project);
        return ProjectMapper.toResponse(saved);
    }
}
