package com.everpath.api.entity;

import com.everpath.api.domain.enums.GoalStatus;
import com.everpath.api.domain.enums.LifeAreaType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entidad encargada de representar
 * una meta dentro del sistema Everpath.
 *
 * Su estructura mantiene compatibilidad
 * con el modelo GoalNode utilizado
 * actualmente en Android.
 */
@Entity
@Table(
        name = "goals"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoalEntity {

    /**
     * Identificador único de la meta.
     *
     * Se almacena como String para
     * mantener compatibilidad total
     * con Android.
     *
     * El valor será generado mediante:
     *
     * UUID.randomUUID().toString()
     */
    @Id
    @Column(
            nullable = false,
            updatable = false,
            length = 36
    )
    private String id;


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


    @Enumerated(
            EnumType.STRING
    )
    @Column(
            nullable = false
    )
    private LifeAreaType lifeArea;


    @Enumerated(
            EnumType.STRING
    )
    @Column(
            nullable = false
    )
    private GoalStatus status;


    @Column(
            nullable = false
    )
    private Boolean xpGranted;


    @Column(
            nullable = false
    )
    private LocalDateTime createdAt;


    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private UserEntity user;
}