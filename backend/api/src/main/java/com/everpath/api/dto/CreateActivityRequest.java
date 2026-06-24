package com.everpath.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO encargado de recibir
 * la información necesaria
 * para crear una actividad.
 *
 * Una actividad siempre
 * pertenece a una meta.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateActivityRequest {


    @NotBlank(
            message = "La meta es obligatoria"
    )
    private String goalId;


    @NotBlank(
            message = "El título es obligatorio"
    )
    private String title;


    @NotBlank(
            message = "La descripción es obligatoria"
    )
    private String description;
}