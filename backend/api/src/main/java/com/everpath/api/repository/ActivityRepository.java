package com.everpath.api.repository;

import com.everpath.api.entity.ActivityEntity;
import com.everpath.api.entity.GoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio encargado de la
 * persistencia de actividades.
 *
 * Spring Data JPA implementará
 * automáticamente todos los métodos.
 */
public interface ActivityRepository
        extends JpaRepository<
        ActivityEntity,
        String
        > {

    /**
     * Obtiene todas las actividades
     * pertenecientes a una meta.
     */
    List<ActivityEntity> findAllByGoal(
            GoalEntity goal
    );

}