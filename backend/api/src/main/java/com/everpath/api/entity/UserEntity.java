package com.everpath.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entidad persistente que representa
 * un usuario registrado dentro de Everpath.
 *
 * Será la entidad raíz del sistema
 * para autenticación, progreso,
 * metas y logros.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {


    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;


    @Column(
            nullable = false,
            length = 100
    )
    private String name;


    @Column(
            nullable = false,
            unique = true,
            length = 120
    )
    private String email;


    @Column(
            nullable = false,
            length = 255
    )
    private String password;


    @Column(
            nullable = false
    )
    private LocalDateTime createdAt;
}