package com.paulo.gestortarefas.features.projects.adapters.outbound.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class ProjectJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String description;

    public ProjectJpaEntity() {}

    public ProjectJpaEntity(Long id, String name, LocalDateTime createdAt, String description) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.description = description;
    }

}
