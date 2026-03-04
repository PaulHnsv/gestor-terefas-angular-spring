package com.paulo.gestortarefas.shared.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
public enum Priority {

    BAIXA(1, "Baixa"),
    MEDIA(2, "Media"),
    ALTA(3, "Alta");

    private final int code;
    private final String description;

    Priority(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() { return code; }
    public String getDescription(){ return description; }

    public static Priority fromCode(int code) {
        return Arrays.stream(values())
                .filter(s -> s.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código inválido: " + code));
    }

}
