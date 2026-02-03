package com.paulo.gestortarefas.features.projects.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class ProjectRequest {

    @NotBlank(message = "O nome é obrigatório")
    @NotNull(message = "O nome é obrigatório")
    @Size(min = 2, max = 80, message = "nome deve ter entre 2 e 80 caracteres")
    private String name;
    private String description;

}
