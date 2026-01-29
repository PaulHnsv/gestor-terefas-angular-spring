package com.paulo.gestortarefas.features.projects.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectResponse {
    private Long id;
    private String name;

    public ProjectResponse() {}

    public ProjectResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
