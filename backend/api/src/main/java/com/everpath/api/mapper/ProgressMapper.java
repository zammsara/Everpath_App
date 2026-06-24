package com.everpath.api.mapper;

import com.everpath.api.dto.AchievementResponse;
import com.everpath.api.dto.UserProgressResponse;
import com.everpath.api.entity.AchievementEntity;
import com.everpath.api.entity.UserProgressEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper encargado de transformar
 * entidades relacionadas con progreso
 * y achievements hacia DTOs de respuesta.
 *
 * Mantiene desacoplada la capa
 * de servicio de la representación REST.
 */
@Component
public class ProgressMapper {

    /**
     * Convierte UserProgressEntity
     * en UserProgressResponse.
     */
    public UserProgressResponse toProgressResponse(
            UserProgressEntity entity
    ) {

        return UserProgressResponse.builder()

                .xp(
                        entity.getXp()
                )

                .build();
    }

    /**
     * Convierte AchievementEntity
     * en AchievementResponse.
     */
    public AchievementResponse toAchievementResponse(
            AchievementEntity entity
    ) {

        return AchievementResponse.builder()

                .id(
                        entity.getId()
                )

                .title(
                        entity.getTitle()
                )

                .description(
                        entity.getDescription()
                )

                .unlocked(
                        entity.getUnlocked()
                )

                .unlockedAt(
                        entity.getUnlockedAt()
                )

                .build();
    }
}