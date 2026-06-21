package com.everpath.api.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Centraliza el manejo de excepciones
 * de toda la aplicación.
 *
 * Convierte excepciones Java
 * en respuestas HTTP consistentes.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja correos duplicados.
     */
    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public ResponseEntity<ErrorResponse> handleValidationError(

            MethodArgumentNotValidException exception

    ) {

        String message =
                exception
                        .getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .findFirst()
                        .map(fieldError ->
                                fieldError.getDefaultMessage()
                        )
                        .orElse(
                                "Error de validación"
                        );

        ErrorResponse response =
                ErrorResponse.builder()
                        .timestamp(
                                LocalDateTime.now()
                        )
                        .status(
                                HttpStatus.BAD_REQUEST.value()
                        )
                        .error(
                                HttpStatus.BAD_REQUEST.getReasonPhrase()
                        )
                        .message(
                                message
                        )
                        .build();

        return ResponseEntity
                .badRequest()
                .body(
                        response
                );
    }
}