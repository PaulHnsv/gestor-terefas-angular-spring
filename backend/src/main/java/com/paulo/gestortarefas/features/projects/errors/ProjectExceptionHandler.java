package com.paulo.gestortarefas.features.projects.errors;

import com.paulo.gestortarefas.features.projects.adapters.inbound.rest.ProjectController;
import com.paulo.gestortarefas.shared.errors.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice(assignableTypes = {ProjectController.class})
public class ProjectExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleDataNotFinding(Exception ex,
                                                         HttpServletRequest req) {
        return build(HttpStatus.NOT_FOUND,
                "BAD_REQUEST",
                ex.getMessage(),
                req,
                null);
    }

    private ResponseEntity<ApiError> build(HttpStatus status,
                                           String error,
                                           String message,
                                           HttpServletRequest req,
                                           Object details) {

        ApiError body = new ApiError(
                OffsetDateTime.now().toString(),
                status.value(),
                error,
                message,
                req.getRequestURI(),
                details
        );

        return ResponseEntity.status(status).body(body);
    }
}
