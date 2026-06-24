package com.everpath.api.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad encargada de almacenar
 * el progreso global del usuario.
 *
 * Actualmente solo persiste XP.
 *
 * El nivel se calcula dinámicamente
 * a partir de la experiencia acumulada.
 */
@Entity
@Table(
        name = "user_progress"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProgressEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;


    @OneToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private UserEntity user;


    @Column(
            nullable = false
    )
    private Integer xp;
}