package com.everpath.api.dto.progress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO encargado de exponer
 * el progreso global del usuario.
 *
 * La información de nivel
 * se calcula dinámicamente
 * a partir de la XP acumulada.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProgressResponse {


    private Integer xp;

    private Integer level;

    private Integer currentLevelXp;

    private Integer requiredXpForNextLevel;

    private Float progress;
}