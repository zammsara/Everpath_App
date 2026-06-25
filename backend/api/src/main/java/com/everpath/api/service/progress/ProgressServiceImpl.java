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

    private final LevelCalculator levelCalculator;

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
                levelCalculator.calculateLevel(
                        xp
                );

        int currentLevelXp =
                levelCalculator.calculateCurrentLevelXp(
                        xp
                );

        int requiredXp =
                levelCalculator.calculateRequiredXpForNextLevel(
                        xp
                );

        float percentage =
                levelCalculator.calculateProgress(
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
}
