package com.paulo.gestortarefas.features.projects.domain.ports.outbound;

import com.paulo.gestortarefas.features.projects.domain.model.Project;

import java.util.List;

public interface ProjectRepository {
    Project save(Project project);
    List<Project> findAll();
    Project findById(long id);
    void delete(long id);
}
