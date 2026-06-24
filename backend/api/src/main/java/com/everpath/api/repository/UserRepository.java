package com.everpath.api.repository;

import com.everpath.api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio encargado de las operaciones
 * de persistencia para usuarios.
 *
 * Spring Data JPA implementará automáticamente
 * todos los métodos necesarios.
 */
public interface UserRepository
        extends JpaRepository<UserEntity, Long> {


    Optional<UserEntity> findByEmail(
            String email
    );


    boolean existsByEmail(
            String email
    );
}