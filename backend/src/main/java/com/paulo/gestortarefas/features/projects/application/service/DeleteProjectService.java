package com.paulo.gestortarefas.features.projects.application.service;

import com.paulo.gestortarefas.features.projects.domain.ports.inbound.DeleteProjectUseCase;
import com.paulo.gestortarefas.features.projects.domain.ports.outbound.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteProjectService implements DeleteProjectUseCase {

    @Autowired
    private ProjectRepository repository;

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
