package com.everpath.api.service.progress;

import com.everpath.api.entity.UserEntity;

/**
 * Servicio encargado de gestionar
 * modificaciones de experiencia.
 *
 * Centraliza toda la lógica de XP
 * para evitar duplicación.
 */
public interface XpService {

    void addXp(

            UserEntity user,
            int amount

    );
}