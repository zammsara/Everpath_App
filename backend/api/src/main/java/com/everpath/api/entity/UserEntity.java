package com.everpath.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<GoalEntity> goals =
            new ArrayList<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<AchievementEntity> achievements =
            new ArrayList<>();

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private UserProgressEntity progress;
}