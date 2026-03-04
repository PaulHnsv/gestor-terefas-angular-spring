package com.paulo.gestortarefas.features.tasks.application.dto;

import com.paulo.gestortarefas.features.projects.domain.model.Project;
import com.paulo.gestortarefas.shared.utils.Priority;
import com.paulo.gestortarefas.shared.utils.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {

    @NotBlank(message = "O campo título é obrigatório")
    @NotNull(message = "O campo título é obrigatório")
    @Size(min = 2, max = 80, message = "Título deve ter entre 2 e 80 caracteres")
    private String title;

    private String description;

    @NotNull(message = "O campo projeto é obrigatório")
    private Project project;

    @NotNull(message = "O campo prioridade não pode ser nulo")
    private Priority priority;

    @NotNull(message = "O campo status não pode ser nulo")
    private Status status;


}
