package com.paulo.gestortarefas.modules.projeto.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class ProjetoDTO {

    private String nome;
    private String descricao;
    private Date dataCriacao;

}
