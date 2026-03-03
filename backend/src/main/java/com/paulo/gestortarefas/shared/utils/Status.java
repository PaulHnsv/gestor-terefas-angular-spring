package com.paulo.gestortarefas.shared.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Status {

    PENDENTE(1, "Pendente"),
    EM_ANDAMENTO(2, "Em andamento"),
    CONCLUIDA(3, "Concluida");

    private final int code;
    private final String description;

    Status(int code, String description) {
        this.code = code;
        this.description = description;
    }

    @JsonValue
    public int getCode() { return code; }

    @JsonCreator
    public static Status fromCode(int code) {
        return Arrays.stream(values())
                .filter(s -> s.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código inválido: " + code));
    }
}
