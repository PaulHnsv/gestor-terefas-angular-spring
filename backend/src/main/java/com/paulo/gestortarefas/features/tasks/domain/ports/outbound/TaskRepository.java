package com.paulo.gestortarefas.features.tasks.domain.ports.outbound;

import com.paulo.gestortarefas.features.tasks.domain.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task save(Task task);
    List<Task> findAll();
    Optional<Task> findById(Long id);
    List<Optional<Task>> findByProjectId(Long id);
    void delete(Long id);
}
