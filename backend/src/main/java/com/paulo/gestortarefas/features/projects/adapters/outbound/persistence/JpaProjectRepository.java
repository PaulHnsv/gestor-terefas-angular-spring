package com.paulo.gestortarefas.features.projects.adapters.outbound.persistence;

import com.paulo.gestortarefas.features.projects.application.dto.ProjectMapper;
import com.paulo.gestortarefas.features.projects.domain.model.Project;
import com.paulo.gestortarefas.features.projects.domain.ports.outbound.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaProjectRepository implements ProjectRepository {

    @Autowired
    private SpringDataProjectRepository springData;

    @Autowired
    private ProjectMapper projectMapper;

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

    public Optional<Project> findById(long id) {
        return springData.findById(id)
                .map(entity -> projectMapper.toDomain(entity));
    }

    @Override
    public void delete(long id) {
        springData.deleteById(id);
    }
}