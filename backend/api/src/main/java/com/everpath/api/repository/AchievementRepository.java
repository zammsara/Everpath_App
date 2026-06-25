package com.everpath.api.repository;

import com.everpath.api.entity.AchievementEntity;
import com.everpath.api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio encargado de la
 * persistencia de achievements
 * desbloqueados por usuarios.
 */
public interface AchievementRepository
        extends JpaRepository<
        AchievementEntity,
        String
        > {

    /**
     * Obtiene todos los achievements
     * desbloqueados de un usuario.
     */
    List<AchievementEntity> findAllByUser(

            UserEntity user

    );

    boolean existsByUserAndId(

            UserEntity user,
            String id

    );
}