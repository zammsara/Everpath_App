package com.everpath.api.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO utilizado para recibir
 * solicitudes de registro.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank(
            message = "El nombre es obligatorio"
    )
    @Size(
            min = 2,
            max = 50,
            message = "El nombre debe tener entre 2 y 50 caracteres"
    )
    private String name;


    @NotBlank(
            message = "El correo es obligatorio"
    )
    @Email(
            message = "Formato de correo inválido"
    )
    @Size(
            max = 120,
            message = "El correo no puede superar 120 caracteres"
    )
    private String email;


    @NotBlank(
            message = "La contraseña es obligatoria"
    )
    @Size(
            min = 8,
            max = 64,
            message = "La contraseña debe tener entre 8 y 64 caracteres"
    )
    private String password;
}