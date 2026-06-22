package com.everpath.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
     * Maneja errores de validación
     * provenientes de @Valid.
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

    /**
     * Maneja correos duplicados.
     */
    @ExceptionHandler(
            EmailAlreadyExistsException.class
    )
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExists(

            EmailAlreadyExistsException exception

    ) {

        ErrorResponse response =
                ErrorResponse.builder()
                        .timestamp(
                                LocalDateTime.now()
                        )
                        .status(
                                HttpStatus.CONFLICT.value()
                        )
                        .error(
                                HttpStatus.CONFLICT.getReasonPhrase()
                        )
                        .message(
                                exception.getMessage()
                        )
                        .build();

        return ResponseEntity
                .status(
                        HttpStatus.CONFLICT
                )
                .body(
                        response
                );
    }

    /**
     * Captura errores inesperados.
     */
    @ExceptionHandler(
            Exception.class
    )
    public ResponseEntity<ErrorResponse> handleGenericException(

            Exception exception

    ) {

        ErrorResponse response =
                ErrorResponse.builder()
                        .timestamp(
                                LocalDateTime.now()
                        )
                        .status(
                                HttpStatus.INTERNAL_SERVER_ERROR.value()
                        )
                        .error(
                                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
                        )
                        .message(
                                "Ha ocurrido un error interno en el servidor"
                        )
                        .build();

        return ResponseEntity
                .status(
                        HttpStatus.INTERNAL_SERVER_ERROR
                )
                .body(
                        response
                );
    }

    /**
     * Maneja errores de autenticación.
     */
    @ExceptionHandler(
            {
                    BadCredentialsException.class,
                    UsernameNotFoundException.class
            }
    )
    public ResponseEntity<ErrorResponse> handleAuthenticationException(

            Exception exception

    ) {

        ErrorResponse response =
                ErrorResponse.builder()

                        .timestamp(
                                LocalDateTime.now()
                        )

                        .status(
                                HttpStatus.UNAUTHORIZED.value()
                        )

                        .error(
                                HttpStatus.UNAUTHORIZED.getReasonPhrase()
                        )

                        .message(
                                "Correo o contraseña incorrectos"
                        )

                        .build();

        return ResponseEntity
                .status(
                        HttpStatus.UNAUTHORIZED
                )
                .body(
                        response
                );
    }

}