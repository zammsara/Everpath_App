package com.everpath.api.entity;

import com.everpath.api.domain.enums.ActivityStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entidad encargada de representar
 * una actividad perteneciente
 * a una meta dentro de Everpath.
 */
@Entity
@Table(
        name = "activities"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityEntity {

    /**
     * UUID compatible
     * con Android.
     */
    @Id
    @Column(
            nullable = false,
            updatable = false,
            length = 36
    )
    private String id;


    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "goal_id",
            nullable = false
    )
    private GoalEntity goal;


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
    private ActivityStatus status;


    @Column(
            nullable = false
    )
    private Boolean xpGranted;


    @Column(
            nullable = false
    )
    private LocalDateTime createdAt;
}