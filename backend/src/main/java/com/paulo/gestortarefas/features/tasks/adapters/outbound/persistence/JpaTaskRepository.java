package com.paulo.gestortarefas.features.tasks.adapters.outbound.persistence;

import com.paulo.gestortarefas.features.tasks.application.dto.TaskMapper;
import com.paulo.gestortarefas.features.tasks.domain.model.Task;
import com.paulo.gestortarefas.features.tasks.domain.ports.outbound.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaTaskRepository implements TaskRepository {

    @Autowired
    private SpringDataTaskRepository springData;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public Task save(Task project) {
        TaskJpaEntity entity = taskMapper.toEntity(project);
        TaskJpaEntity saved = springData.save(entity);
        return taskMapper.toDomain(saved);
    }

    @Override
    public List<Task> findAll() {
        return springData.findAll().stream()
                .map(e -> taskMapper.toDomain(e))
                .toList();
    }

    @Override
    public Optional<Task> findById(Long id) {
        return springData.findById(id)
                .map(entity -> taskMapper.toDomain(entity));
    }

    @Override
    public void delete(Long id) {
        springData.deleteById(id);
    }

    @Override
    public Optional<Task> findByProjectId(Long id){
        return springData.findByProjectId(id)
                .map(entity -> taskMapper.toDomain(entity));
    }
}