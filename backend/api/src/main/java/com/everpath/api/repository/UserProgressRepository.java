package com.everpath.api.repository;

import com.everpath.api.entity.UserEntity;
import com.everpath.api.entity.UserProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio encargado de la
 * persistencia del progreso global
 * de los usuarios.
 *
 * Permite recuperar la XP asociada
 * a un usuario específico.
 */
public interface UserProgressRepository
        extends JpaRepository<
        UserProgressEntity,
        Long
        > {

    /**
     * Obtiene el progreso
     * asociado a un usuario.
     */
    Optional<UserProgressEntity> findByUser(

            UserEntity user

    );
}