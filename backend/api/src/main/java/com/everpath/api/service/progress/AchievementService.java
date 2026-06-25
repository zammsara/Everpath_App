package com.everpath.api.service.progress;

import com.everpath.api.entity.UserEntity;


/**
 * Contrato encargado de gestionar
 * la evaluación y desbloqueo automático
 * de achievements dentro de Everpath.
 *
 * Centraliza las reglas de negocio
 * relacionadas con los losgros para
 * evitar duplicación entre servicios.
 */
public interface AchievementService {

    void evaluateAchievements(
            UserEntity user
    );
}