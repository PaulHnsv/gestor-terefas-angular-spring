package com.paulo.gestortarefas.modules.statuscheck.adapters.inbound.rest;

import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.Map;

@RestController
@RequestMapping
public class StatusCheck {

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