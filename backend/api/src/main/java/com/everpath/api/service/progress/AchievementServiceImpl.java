package com.everpath.api.service.progress;

import com.everpath.api.domain.enums.ActivityStatus;
import com.everpath.api.domain.enums.GoalStatus;
import com.everpath.api.entity.AchievementEntity;
import com.everpath.api.entity.UserEntity;
import com.everpath.api.repository.ActivityRepository;
import com.everpath.api.repository.AchievementRepository;
import com.everpath.api.repository.GoalRepository;
import com.everpath.api.repository.UserProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Implementación encargada de evaluar
 * automáticamente los achievements
 * desbloqueables por un usuario.
 *
 * Centraliza todas las reglas de negocio
 * relacionadas con logros para mantener
 * desacoplados los demás servicios.
 */
@Service
@RequiredArgsConstructor
public class AchievementServiceImpl
        implements AchievementService {

    private final AchievementRepository achievementRepository;

    private final ActivityRepository activityRepository;

    private final GoalRepository goalRepository;

    private final UserProgressRepository userProgressRepository;

    private final LevelCalculator levelCalculator;

    @Override
    public void evaluateAchievements(
            UserEntity user
    ) {

        int xp = userProgressRepository
                .findByUser(user)
                .orElseThrow(() -> new IllegalStateException(
                        "El usuario no posee un progreso registrado."
                ))
                .getXp();

        int level =
                levelCalculator.calculateLevel(
                        xp
                );

        long completedActivities =
                activityRepository.countByGoalUserAndStatus(
                        user,
                        ActivityStatus.COMPLETED
                );

        long completedGoals =
                goalRepository.countByUserAndStatus(
                        user,
                        GoalStatus.COMPLETED
                );

        //-----------------------------------
        // ACTIVIDADES
        //-----------------------------------

        if (completedActivities >= 1) {

            unlockAchievement(
                    user,
                    "ACTIVITY_1"
            );

        }

        if (completedActivities >= 10) {

            unlockAchievement(
                    user,
                    "ACTIVITY_10"
            );

        }

        if (completedActivities >= 50) {

            unlockAchievement(
                    user,
                    "ACTIVITY_50"
            );

        }

        //-----------------------------------
        // METAS
        //-----------------------------------

        if (completedGoals >= 1) {

            unlockAchievement(
                    user,
                    "GOAL_1"
            );

        }

        if (completedGoals >= 10) {

            unlockAchievement(
                    user,
                    "GOAL_10"
            );

        }

        //-----------------------------------
        // XP
        //-----------------------------------

        if (xp >= 100) {

            unlockAchievement(
                    user,
                    "XP_100"
            );

        }

        if (xp >= 500) {

            unlockAchievement(
                    user,
                    "XP_500"
            );

        }

        if (xp >= 1000) {

            unlockAchievement(
                    user,
                    "XP_1000"
            );

        }

        //-----------------------------------
        // NIVEL
        //-----------------------------------

        if (level >= 5) {

            unlockAchievement(
                    user,
                    "LEVEL_5"
            );

        }

    }

    /**
     * Desbloquea un achievement
     * únicamente si aún no existe
     * para el usuario.
     */
    private void unlockAchievement(

            UserEntity user,
            String achievementId

    ) {

        if (

                achievementRepository.existsByUserAndId(
                        user,
                        achievementId
                )

        ) {

            return;

        }

        AchievementCatalog.Definition definition =
                AchievementCatalog.ACHIEVEMENTS

                        .stream()

                        .filter(
                                achievement ->
                                        achievement.getId()
                                                .equals(
                                                        achievementId
                                                )
                        )

                        .findFirst()

                        .orElseThrow(() -> new IllegalStateException(
                                "Logro no encontrado en el catalógo de logros: " + achievementId
                        ));

        AchievementEntity achievement =
                AchievementEntity.builder()

                        .id(
                                definition.getId()
                        )

                        .title(
                                definition.getTitle()
                        )

                        .description(
                                definition.getDescription()
                        )

                        .user(
                                user
                        )

                        .unlocked(
                                true
                        )

                        .unlockedAt(
                                LocalDateTime.now()
                        )

                        .build();

        achievementRepository.save(
                achievement
        );

    }

}