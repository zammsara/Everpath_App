package com.everpath.api.mapper;

import com.everpath.api.dto.goal.CreateGoalRequest;
import com.everpath.api.dto.goal.UpdateGoalRequest;
import com.everpath.api.dto.goal.GoalResponse;
import com.everpath.api.entity.GoalEntity;
import com.everpath.api.domain.enums.GoalStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Mapper encargado de transformar
 * DTOs relacionados con Goal ↔ Entity.
 *
 * Mantiene la capa de servicio limpia
 * separando lógica de conversión.
 */
@Component
public class GoalMapper {

    /**
     * Convierte CreateGoalRequest → GoalEntity
     */
    public GoalEntity toEntity(CreateGoalRequest request) {

        return GoalEntity.builder()
                .id(UUID.randomUUID().toString())
                .title(request.getTitle())
                .description(request.getDescription())
                .lifeArea(request.getLifeArea())
                .status(GoalStatus.ACTIVE)
                .xpGranted(false)
                .createdAt(LocalDateTime.now())
                .build();
    }

    /**
     * Aplica cambios de UpdateGoalRequest
     * sobre una entidad existente.
     */
    public void updateEntity(
            GoalEntity entity,
            UpdateGoalRequest request
    ) {

        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setLifeArea(request.getLifeArea());
        entity.setStatus(request.getStatus());
    }

    /**
     * Convierte GoalEntity → GoalResponse
     */
    public GoalResponse toResponse(GoalEntity entity) {

        return GoalResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .lifeArea(entity.getLifeArea())
                .status(entity.getStatus())
                .xpGranted(entity.getXpGranted())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}