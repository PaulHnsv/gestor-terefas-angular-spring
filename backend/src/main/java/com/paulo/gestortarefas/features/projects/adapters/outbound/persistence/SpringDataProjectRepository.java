package com.paulo.gestortarefas.features.projects.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProjectRepository extends JpaRepository<ProjectJpaEntity, Long> {
}
