package com.paulo.gestortarefas.features.projects.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Project {

    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private String description;

    public Project(Long id, String name, LocalDateTime createdAt, String description) {
        validateName(name);
        this.id = id;
        this.name = name.trim();
        this.createdAt = createdAt;
        this.description = description;
    }

    private void validateName(String name){
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("o nome é obrigatório");
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Project project)) return false;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
