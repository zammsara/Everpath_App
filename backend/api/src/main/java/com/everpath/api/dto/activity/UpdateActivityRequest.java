package com.everpath.api.dto.activity;

import com.everpath.api.domain.enums.ActivityStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO utilizado para
 * actualizar actividades.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateActivityRequest {

    @NotBlank(
            message = "El título es obligatorio"
    )
    private String title;

    @NotBlank(
            message = "La descripción es obligatoria"
    )
    private String description;

    @NotNull(
            message = "El estado es obligatorio"
    )
    private ActivityStatus status;
}