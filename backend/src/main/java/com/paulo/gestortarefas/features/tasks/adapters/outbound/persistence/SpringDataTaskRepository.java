package com.paulo.gestortarefas.features.tasks.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTaskRepository extends JpaRepository<TaskJpaEntity, Long> {
}
