package com.everpath.api.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Representa la estructura estándar
 * utilizada por la API para responder
 * ante errores controlados.
 */
@Getter
@Builder
public class ErrorResponse {

    private LocalDateTime timestamp;

    private int status;

    private String error;

    private String message;
}