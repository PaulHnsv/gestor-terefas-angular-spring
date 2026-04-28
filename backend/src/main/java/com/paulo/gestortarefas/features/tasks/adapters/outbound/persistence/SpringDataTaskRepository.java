package com.paulo.gestortarefas.features.tasks.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataTaskRepository extends JpaRepository<TaskJpaEntity, Long> {

    List<TaskJpaEntity> findByProjectId(Long id);
}
