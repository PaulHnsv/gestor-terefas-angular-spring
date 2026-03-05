package com.paulo.gestortarefas.features.projects.application.service;

import com.paulo.gestortarefas.features.projects.application.dto.ProjectMapper;
import com.paulo.gestortarefas.features.projects.application.dto.ProjectResponse;
import com.paulo.gestortarefas.features.projects.domain.model.Project;
import com.paulo.gestortarefas.features.projects.domain.ports.inbound.ListProjectsUseCase;
import com.paulo.gestortarefas.features.projects.domain.ports.outbound.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class ListProjectsService implements ListProjectsUseCase {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public List<ProjectResponse> list() {
        return projectMapper.toResponseList(repository.findAll());
    }

    @Override
    public ProjectResponse findById(Long id) {
        Project project = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Projeto não encontrado"
                ));;
        return projectMapper.toResponse(project);
    }

}
