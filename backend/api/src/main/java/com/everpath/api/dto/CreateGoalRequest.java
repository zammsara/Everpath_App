package com.everpath.api.dto;

import com.everpath.api.domain.enums.LifeAreaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO encargado de recibir la información
 * necesaria para crear una nueva meta.
 *
 * userId se utiliza para identificar
 * al propietario de la meta
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateGoalRequest {

    @NotNull(
            message = "El usuario es obligatorio"
    )
    private Long userId;


    @NotBlank(
            message = "El título es obligatorio"
    )
    private String title;


    @NotBlank(
            message = "La descripción es obligatoria"
    )
    private String description;


    @NotNull(
            message = "El área de vida es obligatoria"
    )
    private LifeAreaType lifeArea;

}