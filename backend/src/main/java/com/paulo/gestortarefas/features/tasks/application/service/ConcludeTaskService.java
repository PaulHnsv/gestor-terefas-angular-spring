package com.paulo.gestortarefas.features.tasks.application.service;

import com.paulo.gestortarefas.features.tasks.adapters.outbound.persistence.SpringDataTaskRepository;
import com.paulo.gestortarefas.features.tasks.domain.model.Task;
import com.paulo.gestortarefas.features.tasks.domain.ports.inbound.ConcludeTaskUseCase;
import com.paulo.gestortarefas.features.tasks.domain.ports.outbound.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ConcludeTaskService  implements ConcludeTaskUseCase {

    @Autowired
    private TaskRepository repository;

    @Override
    public void concludeTask(Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tarefa não encontrada"
        ));
        task.concludeTask();
        repository.save(task);
    }

}
