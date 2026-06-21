package com.everpath.api.exception;

/**
 * Excepción lanzada cuando se intenta
 * registrar un usuario con un correo
 * que ya existe en la base de datos.
 */
public class EmailAlreadyExistsException
        extends RuntimeException {

    public EmailAlreadyExistsException(
            String email
    ) {
        super(
                "Ya existe un usuario registrado con el correo: "
                        + email
        );
    }
}