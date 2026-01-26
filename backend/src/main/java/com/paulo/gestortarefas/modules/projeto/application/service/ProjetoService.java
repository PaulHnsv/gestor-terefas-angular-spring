package com.paulo.gestortarefas.modules.projeto.application.service;

import com.paulo.gestortarefas.modules.projeto.application.dto.ProjetoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjetoService {

    public List<ProjetoDTO> getProjetos(){
        int interator = 0;
        ArrayList<ProjetoDTO> lista = new ArrayList<>();

        while (interator <5){
            ProjetoDTO projeto = new ProjetoDTO();
            projeto.setNome("teste Nome");
            projeto.setDataCriacao(new Date());
            projeto.setDescricao("teste Descrição");

            lista.add(projeto);
            interator++;
        }

        return lista;
    }

    public String postProjeto(ProjetoDTO projetoDTO){
        System.out.println("salvo com sucesso");
        return "Projeto Criado com sucesso";
    }
}
