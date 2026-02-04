package com.paulo.gestortarefas.features.projects.application.service;

import com.paulo.gestortarefas.features.projects.application.dto.ProjectMapper;
import com.paulo.gestortarefas.features.projects.application.dto.ProjectRequest;
import com.paulo.gestortarefas.features.projects.application.dto.ProjectResponse;
import com.paulo.gestortarefas.features.projects.domain.model.Project;
import com.paulo.gestortarefas.features.projects.domain.ports.inbound.UpdateProjectUseCase;
import com.paulo.gestortarefas.features.projects.domain.ports.outbound.ProjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdateProjectService implements UpdateProjectUseCase {

    @Autowired
    private ProjectRepository repository;

    @Override
    public ProjectResponse update(Long id, ProjectRequest request) {
        Project entity = repository.findById(id);
        BeanUtils.copyProperties(request, entity);
        Project saved = repository.save(entity);
        return ProjectMapper.toResponse(saved);
    }
}
