package com.paulo.gestortarefas.features.tasks.adapters.outbound.persistence;

import com.paulo.gestortarefas.features.projects.adapters.outbound.persistence.ProjectJpaEntity;
import com.paulo.gestortarefas.shared.utils.Priority;
import com.paulo.gestortarefas.shared.utils.Status;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
@Table(name = "tasks")
public class TaskJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Priority priority;

    @Column(nullable = false)
    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime completedAt;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectJpaEntity project;

    public TaskJpaEntity() {}

    public TaskJpaEntity(Long id, String title, Priority priority, Status status, LocalDateTime createdAt, LocalDateTime completedAt, String description, ProjectJpaEntity project) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
        this.description = description;
        this.project = project;
    }
}
