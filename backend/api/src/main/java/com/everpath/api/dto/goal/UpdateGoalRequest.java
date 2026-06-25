package com.everpath.api.dto.goal;

import com.everpath.api.domain.enums.GoalStatus;
import com.everpath.api.domain.enums.LifeAreaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO utilizado para actualizar
 * una meta existente.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateGoalRequest {

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


    @NotNull(
            message = "El estado es obligatorio"
    )
    private GoalStatus status;

}