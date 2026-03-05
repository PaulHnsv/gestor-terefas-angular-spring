package com.paulo.gestortarefas.features.projects.application.service;

import com.paulo.gestortarefas.features.projects.application.dto.ProjectMapper;
import com.paulo.gestortarefas.features.projects.application.dto.ProjectRequest;
import com.paulo.gestortarefas.features.projects.application.dto.ProjectResponse;
import com.paulo.gestortarefas.features.projects.domain.model.Project;
import com.paulo.gestortarefas.features.projects.domain.ports.inbound.UpdateProjectUseCase;
import com.paulo.gestortarefas.features.projects.domain.ports.outbound.ProjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateProjectService implements UpdateProjectUseCase {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public ProjectResponse update(Long id, ProjectRequest request) {
        Project entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Projeto não encontrada"
                ));
        BeanUtils.copyProperties(request, entity);
        Project saved = repository.save(entity);
        return projectMapper.toResponse(saved);
    }
}
