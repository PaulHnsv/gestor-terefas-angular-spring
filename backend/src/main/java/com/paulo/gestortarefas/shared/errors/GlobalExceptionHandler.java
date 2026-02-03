package com.paulo.gestortarefas.shared.errors;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex,
                                                     HttpServletRequest req) {

        List<Map<String, Object>> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::mapFieldError)
                .toList();

        return build(HttpStatus.BAD_REQUEST,
                "VALIDATION_ERROR",
                "Existem campos inválidos na requisição.",
                req,
                fieldErrors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleNotReadable(HttpMessageNotReadableException ex,
                                                      HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST,
                "INVALID_JSON",
                "JSON inválido ou malformado.",
                req,
                null);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                       HttpServletRequest req) {

        String msg = "Parâmetro '%s' com valor '%s' é inválido."
                .formatted(ex.getName(), ex.getValue());

        return build(HttpStatus.BAD_REQUEST,
                "TYPE_MISMATCH",
                msg,
                req,
                Map.of(
                        "param", ex.getName(),
                        "value", ex.getValue(),
                        "expectedType", ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown"
                ));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiError> handleMissingParam(MissingServletRequestParameterException ex,
                                                       HttpServletRequest req) {

        String msg = "Parâmetro obrigatório '%s' não foi informado.".formatted(ex.getParameterName());

        return build(HttpStatus.BAD_REQUEST,
                "MISSING_PARAMETER",
                msg,
                req,
                Map.of("param", ex.getParameterName()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex,
                                                  HttpServletRequest req) {
        // aqui você logaria com stacktrace (log.error)
        return build(HttpStatus.INTERNAL_SERVER_ERROR,
                "INTERNAL_ERROR",
                "Erro interno inesperado.",
                req,
                null);
    }

    private Map<String, Object> mapFieldError(FieldError fe) {
        Map<String, Object> m = new HashMap<>();
        m.put("field", fe.getField());
        m.put("rejectedValue", fe.getRejectedValue());
        m.put("message", fe.getDefaultMessage());
        return m;
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