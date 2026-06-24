package com.everpath.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entidad encargada de representar
 * un achievement desbloqueado
 * por un usuario.
 */
@Entity
@Table(
        name = "achievements"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AchievementEntity {

    @Id
    @Column(
            nullable = false,
            updatable = false,
            length = 100
    )
    private String id;


    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private UserEntity user;


    @Column(
            nullable = false,
            length = 100
    )
    private String title;


    @Column(
            nullable = false,
            length = 500
    )
    private String description;


    @Column(
            nullable = false
    )
    private Boolean unlocked;


    private LocalDateTime unlockedAt;
}
