package com.everpath.api.service.goal;

import com.everpath.api.domain.enums.GoalStatus;
import com.everpath.api.dto.goal.CreateGoalRequest;
import com.everpath.api.dto.goal.GoalResponse;
import com.everpath.api.dto.goal.UpdateGoalRequest;
import com.everpath.api.entity.GoalEntity;
import com.everpath.api.entity.UserEntity;
import com.everpath.api.mapper.GoalMapper;
import com.everpath.api.repository.GoalRepository;
import com.everpath.api.repository.UserRepository;
import com.everpath.api.service.progress.XpService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación de la lógica
 * de negocio relacionada
 * con metas.
 */
@Service
@RequiredArgsConstructor
public class GoalServiceImpl
        implements GoalService {

    private final GoalRepository goalRepository;

    private final UserRepository userRepository;

    private final GoalMapper goalMapper;

    private final XpService xpService;

    @Transactional
    @Override
    public GoalResponse createGoal(
            CreateGoalRequest request
    ) {

        UserEntity user =
                userRepository.findById(
                                request.getUserId()
                        )
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                        "Usuario no encontrado"
                                )
                        );

        GoalEntity goal =
                goalMapper.toEntity(
                        request
                );

        goal.setUser(
                user
        );

        GoalEntity savedGoal =
                goalRepository.save(
                        goal
                );

        return goalMapper.toResponse(
                savedGoal
        );
    }

    @Override
    public GoalResponse getGoalById(
            String goalId
    ) {

        GoalEntity goal =
                goalRepository.findById(
                                goalId
                        )
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                        "Meta no encontrada"
                                )
                        );

        return goalMapper.toResponse(
                goal
        );
    }

    @Override
    public List<GoalResponse> getGoalsByUser(
            Long userId
    ) {

        UserEntity user =
                userRepository.findById(
                                userId
                        )
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                        "Usuario no encontrado"
                                )
                        );

        return goalRepository
                .findAllByUser(
                        user
                )
                .stream()
                .map(
                        goalMapper::toResponse
                )
                .toList();
    }

    @Transactional
    @Override
    public GoalResponse updateGoal(
            String goalId,
            UpdateGoalRequest request
    ) {

        GoalEntity goal =
                goalRepository.findById(
                                goalId
                        )
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                        "Meta no encontrada"
                                )
                        );

        GoalStatus previousStatus =
                goal.getStatus();

        goalMapper.updateEntity(
                goal,
                request
        );

        if (

                previousStatus != GoalStatus.COMPLETED

                        &&

                        request.getStatus()
                                == GoalStatus.COMPLETED

        ) {

            completeGoal(
                    goal
            );

        }

        GoalEntity updatedGoal =
                goalRepository.save(
                        goal
                );

        return goalMapper.toResponse(
                updatedGoal
        );
    }


    @Transactional
    @Override
    public void completeGoal(
            GoalEntity goal
    ) {

        if (
                goal.getStatus()
                        == GoalStatus.COMPLETED
                        &&
                        goal.getXpGranted()

        ) {

            return;

        }

        goal.setStatus(
                GoalStatus.COMPLETED
        );

        if (
                !goal.getXpGranted()
        ) {
            xpService.addXp(
                    goal.getUser(),
                    100
            );

            goal.setXpGranted(
                    true
            );

        }

        goalRepository.save(
                goal
        );
    }

    @Transactional
    @Override
    public void deleteGoal(
            String goalId
    ) {

        GoalEntity goal =
                goalRepository.findById(
                                goalId
                        )
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                        "Meta no encontrada"
                                )
                        );

        goalRepository.delete(
                goal
        );
    }

}