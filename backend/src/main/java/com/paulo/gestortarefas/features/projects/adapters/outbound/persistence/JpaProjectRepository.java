package com.paulo.gestortarefas.features.projects.adapters.outbound.persistence;

import com.paulo.gestortarefas.features.projects.domain.model.Project;
import com.paulo.gestortarefas.features.projects.domain.ports.outbound.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaProjectRepository implements ProjectRepository {

    @Autowired
    private SpringDataProjectRepository springData;

    @Override
    public Project save(Project project) {
        ProjectJpaEntity entity = new ProjectJpaEntity(project.getId(), project.getName(), project.getCreatedAt(), project.getDescription());
        ProjectJpaEntity saved = springData.save(entity);
        return new Project(saved.getId(), saved.getName(), saved.getCreatedAt(), saved.getDescription());
    }

    @Override
    public List<Project> findAll() {
        return springData.findAll()
                .stream()
                .map(e -> new Project(e.getId(), e.getName(), e.getCreatedAt(), e.getDescription()))
                .toList();
    }

    public Project findById(long id) {
        return springData.findById(id)
                .map(entity -> new Project(
                entity.getId(), entity.getName(), entity.getCreatedAt(), entity.getDescription()))
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado com id: " + id));

    }

    @Override
    public void delete(long id) {
        springData.deleteById(id);
    }
}