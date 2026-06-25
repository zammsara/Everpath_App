package com.everpath.api.dto.goal;

import com.everpath.api.domain.enums.GoalStatus;
import com.everpath.api.domain.enums.LifeAreaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO encargado de devolver
 * información de una meta
 * al cliente Android.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoalResponse {

    private String id;

    private String title;

    private String description;

    private LifeAreaType lifeArea;

    private GoalStatus status;

    private Boolean xpGranted;

    private LocalDateTime createdAt;

}