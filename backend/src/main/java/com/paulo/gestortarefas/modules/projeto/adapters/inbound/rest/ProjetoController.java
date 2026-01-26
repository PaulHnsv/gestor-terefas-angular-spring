package com.paulo.gestortarefas.modules.projeto.adapters.inbound.rest;

import com.paulo.gestortarefas.modules.projeto.application.dto.ProjetoDTO;
import com.paulo.gestortarefas.modules.projeto.application.service.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @GetMapping
    public List<ProjetoDTO> getProjetos() {
        return projetoService.getProjetos();
    }

    @PostMapping
    public String postProjeto(@Valid @RequestBody ProjetoDTO projetoDTO) {
        return projetoService.postProjeto(projetoDTO);
    }

}