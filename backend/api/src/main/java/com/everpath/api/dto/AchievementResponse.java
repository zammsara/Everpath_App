package com.everpath.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO encargado de devolver
 * información de achievements
 * hacia Android.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AchievementResponse {

    /**
     * Identificador del achievement.
     *
     * Ejemplo:
     * ACTIVITY_1
     * XP_100
     * LEVEL_5
     */
    private String id;

    private String title;

    private String description;

    private Boolean unlocked;

    private LocalDateTime unlockedAt;
}