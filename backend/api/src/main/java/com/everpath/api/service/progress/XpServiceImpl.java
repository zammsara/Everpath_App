package com.everpath.api.service.progress;

import com.everpath.api.entity.UserEntity;
import com.everpath.api.entity.UserProgressEntity;
import com.everpath.api.repository.UserProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación encargada
 * de modificar experiencia
 * persistida en base de datos.
 */
@Service
@RequiredArgsConstructor
public class XpServiceImpl
        implements XpService {

    private final UserProgressRepository userProgressRepository;
    private final AchievementService achievementService;

    @Transactional
    @Override
    public void addXp(

            UserEntity user,
            int amount

    ) {

        UserProgressEntity progress =
                userProgressRepository
                        .findByUser(user)

                        .orElseGet(
                                () -> {
                                    UserProgressEntity created =

                                            UserProgressEntity.builder()

                                                    .user(user)
                                                    .xp(0)
                                                    .build();

                                    return userProgressRepository.save(
                                            created
                                    );
                                }
                        );

        progress.setXp(
                progress.getXp()
                        + amount

        );

        userProgressRepository.save(
                progress
        );

        achievementService.evaluateAchievements(
                user
        );
    }
}