package com.paulo.gestortarefas.features.tasks.domain.ports.outbound;

import com.paulo.gestortarefas.features.tasks.domain.model.Task;

import java.util.List;

public interface TaskRepository {
    Task save(Task project);
    List<Task> findAll();
    Task findById(long id);
    void delete(long id);
}
