package com.everpath.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO encargado de recibir
 * las credenciales enviadas
 * durante el inicio de sesión.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

    @NotBlank(
            message = "El correo es obligatorio"
    )
    @Email(
            message = "Formato de correo inválido"
    )
    private String email;


    @NotBlank(
            message = "La contraseña es obligatoria"
    )
    private String password;

}