package com.everpath.api.repository;

import com.everpath.api.entity.GoalEntity;
import com.everpath.api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio encargado de la
 * persistencia de metas.
 *
 * Spring Data JPA implementará
 * automáticamente todos los métodos.
 */
public interface GoalRepository
        extends JpaRepository<
        GoalEntity,
        String
        > {

    /**
     * Obtiene todas las metas
     * pertenecientes a un usuario.
     */
    List<GoalEntity> findAllByUser(
            UserEntity user
    );

}