package com.paulo.gestortarefas.modules.projeto.adapters.inbound.rest;

import com.paulo.gestortarefas.modules.projeto.application.dto.ProjetoDTO;
import com.paulo.gestortarefas.modules.projeto.application.usecase.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @GetMapping
    public List<ProjetoDTO> getProjetos() {
        return projetoService.getProjetos();
    }

}