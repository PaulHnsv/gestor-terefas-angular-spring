package com.paulo.gestortarefas.features.tasks.application.service;

import com.paulo.gestortarefas.features.tasks.domain.ports.inbound.DeleteTaskUseCase;
import com.paulo.gestortarefas.features.tasks.domain.ports.outbound.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteProjectService implements DeleteTaskUseCase {

    @Autowired
    private TaskRepository repository;

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
