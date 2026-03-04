package com.paulo.gestortarefas.features.tasks.application.dto;

import com.paulo.gestortarefas.features.projects.domain.model.Project;
import com.paulo.gestortarefas.shared.utils.Priority;
import com.paulo.gestortarefas.shared.utils.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private Project project;

    public TaskResponse() {}

    public TaskResponse(Long id, String title, String description, Status status, Priority priority, Project project) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.project = project;
    }
}
