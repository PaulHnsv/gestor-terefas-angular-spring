package com.paulo.gestortarefas.shared.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Role {

    USER(1, "Usuário"),
    ADMIN(2, "Administrador");

    private final int code;
    private final String description;

    Role(int code, String description) {
        this.code = code;
        this.description = description;
    }

    @JsonValue
    public int getCode() { return code; }

    @JsonCreator
    public static Role fromCode(int code) {
        return Arrays.stream(values())
                .filter(s -> s.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código inválido: " + code));
    }
}
