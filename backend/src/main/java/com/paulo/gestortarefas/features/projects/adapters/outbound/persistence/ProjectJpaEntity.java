package com.paulo.gestortarefas.features.projects.adapters.outbound.persistence;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "projects")
public class ProjectJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public ProjectJpaEntity() {}

    public ProjectJpaEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
