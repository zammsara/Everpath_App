package com.everpath.api.mapper;

import com.everpath.api.domain.enums.ActivityStatus;
import com.everpath.api.dto.activity.ActivityResponse;
import com.everpath.api.dto.activity.CreateActivityRequest;
import com.everpath.api.dto.activity.UpdateActivityRequest;
import com.everpath.api.entity.ActivityEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Mapper encargado de transformar
 * DTOs relacionados con Activity ↔ Entity.
 *
 * Mantiene la capa de servicio limpia
 * separando la lógica de conversión.
 */
@Component
public class ActivityMapper {

    /**
     * Convierte CreateActivityRequest
     * en ActivityEntity.
     */
    public ActivityEntity toEntity(
            CreateActivityRequest request
    ) {

        return ActivityEntity.builder()

                .id(
                        UUID.randomUUID().toString()
                )

                .title(
                        request.getTitle()
                )

                .description(
                        request.getDescription()
                )

                .status(
                        ActivityStatus.PENDING
                )

                .xpGranted(
                        false
                )

                .createdAt(
                        LocalDateTime.now()
                )

                .build();
    }

    /**
     * Aplica cambios sobre una
     * actividad existente.
     */
    public void updateEntity(
            ActivityEntity entity,
            UpdateActivityRequest request
    ) {

        entity.setTitle(
                request.getTitle()
        );

        entity.setDescription(
                request.getDescription()
        );

        entity.setStatus(
                request.getStatus()
        );
    }

    /**
     * Convierte ActivityEntity
     * en ActivityResponse.
     */
    public ActivityResponse toResponse(
            ActivityEntity entity
    ) {

        return ActivityResponse.builder()

                .id(
                        entity.getId()
                )

                .goalId(
                        entity.getGoal().getId()
                )

                .title(
                        entity.getTitle()
                )

                .description(
                        entity.getDescription()
                )

                .status(
                        entity.getStatus()
                )

                .xpGranted(
                        entity.getXpGranted()
                )

                .createdAt(
                        entity.getCreatedAt()
                )

                .build();
    }
}