package com.paulo.gestortarefas.shared.errors;

public record ApiError(
        String timestamp,
        int status,
        String error,
        String message,
        String path,
        Object details
) {}