package com.paulo.gestortarefas.features.tasks.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataTaskRepository extends JpaRepository<TaskJpaEntity, Long> {

    Optional<TaskJpaEntity> findByProjectId(Long id);
}
