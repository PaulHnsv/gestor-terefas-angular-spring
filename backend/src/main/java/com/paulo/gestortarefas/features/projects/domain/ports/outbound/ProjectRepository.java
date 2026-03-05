package com.paulo.gestortarefas.features.projects.domain.ports.outbound;

import com.paulo.gestortarefas.features.projects.domain.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    Project save(Project project);
    List<Project> findAll();
    Optional<Project> findById(long id);
    void delete(long id);
}
