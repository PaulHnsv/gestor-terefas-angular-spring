package com.paulo.gestortarefas.features.projects.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class ProjectRequest {

    @NotBlank(message = "O nome é obrigatório")
    @NotNull(message = "O nome é obrigatório")
    private String name;
    private String description;

}
