package com.everpath.api.service;

import com.everpath.api.dto.AchievementResponse;
import com.everpath.api.dto.UserProgressResponse;

import java.util.List;

/**
 * Servicio encargado de gestionar
 * toda la lógica relacionada con
 * progreso, niveles y achievements.
 */
public interface ProgressService {

    /**
     * Obtiene el progreso global
     * de un usuario.
     */
    UserProgressResponse getUserProgress(
            Long userId
    );

    /**
     * Obtiene todos los achievements
     * desbloqueados por un usuario.
     */
    List<AchievementResponse> getAchievements(
            Long userId
    );
}