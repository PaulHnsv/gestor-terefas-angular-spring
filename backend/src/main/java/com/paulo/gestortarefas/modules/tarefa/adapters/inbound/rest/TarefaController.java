package com.paulo.gestortarefas.modules.tarefa.adapters.inbound.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.Map;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

//    private final CriarTarefaService criarTarefa;

//    public TarefaController(CriarTarefaService criarTarefa) {
//        this.criarTarefa = criarTarefa;
//    }

//    @PostMapping
//    public ResponseEntity<?> criar(@RequestBody CriarTarefaRequest req) {
//        var tarefa = criarTarefa.executar(req.titulo());
//        return ResponseEntity.ok(TarefaResponse.from(tarefa));
//    }
//
//    public record CriarTarefaRequest(String titulo) {}

    @GetMapping("/api/ping")
    public Map<String, Object> ping() {
        return Map.of(
                "message", "pong",
                "timestamp", OffsetDateTime.now().toString()
        );
    }

    @GetMapping("/api/version")
    public Map<String, Object> version() {
        return Map.of(
                "name", "meu-projeto-estudos",
                "version", "0.0.1",
                "timestamp", OffsetDateTime.now().toString()
        );
    }
}