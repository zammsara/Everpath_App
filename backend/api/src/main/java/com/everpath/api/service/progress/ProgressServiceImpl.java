package com.everpath.api.service.progress;

import com.everpath.api.dto.progress.AchievementResponse;
import com.everpath.api.dto.progress.UserProgressResponse;
import com.everpath.api.entity.UserEntity;
import com.everpath.api.entity.UserProgressEntity;
import com.everpath.api.mapper.ProgressMapper;
import com.everpath.api.repository.AchievementRepository;
import com.everpath.api.repository.UserProgressRepository;
import com.everpath.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación de ProgressService.
 *
 * Gestiona progreso global,
 * niveles y achievements.
 */
@Service
@RequiredArgsConstructor
public class ProgressServiceImpl
        implements ProgressService {

    private final UserRepository userRepository;

    private final UserProgressRepository userProgressRepository;

    private final AchievementRepository achievementRepository;

    private final ProgressMapper progressMapper;

    @Override
    public UserProgressResponse getUserProgress(
            Long userId
    ) {

        UserEntity user =
                userRepository
                        .findById(userId)
                        .orElseThrow(
                                () ->
                                        new EntityNotFoundException(
                                                "Usuario no encontrado"
                                        )
                        );

        UserProgressEntity progress =
                userProgressRepository
                        .findByUser(user)
                        .orElseThrow(
                                () ->
                                        new EntityNotFoundException(
                                                "Progreso no encontrado"
                                        )
                        );

        int xp =
                progress.getXp();

        int level =
                calculateLevel(
                        xp
                );

        int currentLevelXp =
                calculateCurrentLevelXp(
                        xp
                );

        int requiredXp =
                calculateRequiredXpForNextLevel(
                        xp
                );

        float percentage =
                calculateProgress(
                        xp
                );

        UserProgressResponse response =
                progressMapper
                        .toProgressResponse(
                                progress
                        );

        response.setLevel(
                level
        );

        response.setCurrentLevelXp(
                currentLevelXp
        );

        response.setRequiredXpForNextLevel(
                requiredXp
        );

        response.setProgress(
                percentage
        );

        return response;
    }

    @Override
    public List<AchievementResponse> getAchievements(
            Long userId
    ) {

        UserEntity user =
                userRepository
                        .findById(userId)
                        .orElseThrow(
                                () ->
                                        new EntityNotFoundException(
                                                "Usuario no encontrado"
                                        )
                        );

        return achievementRepository

                .findAllByUser(
                        user
                )

                .stream()

                .map(
                        progressMapper::
                                toAchievementResponse
                )

                .toList();
    }

    /**
     * Calcula nivel
     * a partir de XP.
     */
    private int calculateLevel(
            int xp
    ) {

        if (xp >= 1000) {
            return 5;
        }

        if (xp >= 500) {
            return 4;
        }

        if (xp >= 250) {
            return 3;
        }

        if (xp >= 100) {
            return 2;
        }

        return 1;
    }

    /**
     * XP obtenida dentro
     * del nivel actual.
     */
    private int calculateCurrentLevelXp(
            int xp
    ) {

        if (xp >= 1000) {
            return xp - 1000;
        }

        if (xp >= 500) {
            return xp - 500;
        }

        if (xp >= 250) {
            return xp - 250;
        }

        if (xp >= 100) {
            return xp - 100;
        }

        return xp;
    }

    /**
     * XP necesaria para
     * el siguiente nivel.
     */
    private int calculateRequiredXpForNextLevel(
            int xp
    ) {

        if (xp >= 1000) {
            return 1000;
        }

        if (xp >= 500) {
            return 500;
        }

        if (xp >= 250) {
            return 250;
        }

        if (xp >= 100) {
            return 150;
        }

        return 100;
    }

    /**
     * Porcentaje visual
     * del progreso actual.
     */
    private float calculateProgress(
            int xp
    ) {

        if (xp >= 1000) {
            return 1f;
        }

        if (xp >= 500) {

            return (xp - 500)
                    / 500f;
        }

        if (xp >= 250) {

            return (xp - 250)
                    / 250f;
        }

        if (xp >= 100) {

            return (xp - 100)
                    / 150f;
        }

        return xp / 100f;
    }
}
