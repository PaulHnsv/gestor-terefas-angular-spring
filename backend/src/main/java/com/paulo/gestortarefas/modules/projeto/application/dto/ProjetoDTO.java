package com.paulo.gestortarefas.modules.projeto.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Data
@Getter
@Setter
public class ProjetoDTO {

    @NotNull(message = "O nome é obrigatório")
    private String nome;
    private String descricao;
    private Date dataCriacao;

}
