package com.paulo.gestortarefas.features.tasks.domain.ports.outbound;

import com.paulo.gestortarefas.features.tasks.domain.model.Task;

import java.util.List;

public interface TaskRepository {
    Task save(Task task);
    List<Task> findAll();
    Task findById(long id);
    Task findByProjectId(Long id);
    void delete(long id);
}
