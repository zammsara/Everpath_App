package com.everpath.api.service.activity;

import com.everpath.api.domain.enums.ActivityStatus;
import com.everpath.api.dto.activity.ActivityResponse;
import com.everpath.api.dto.activity.CreateActivityRequest;
import com.everpath.api.dto.activity.UpdateActivityRequest;
import com.everpath.api.entity.ActivityEntity;
import com.everpath.api.entity.GoalEntity;
import com.everpath.api.mapper.ActivityMapper;
import com.everpath.api.repository.ActivityRepository;
import com.everpath.api.repository.GoalRepository;
import com.everpath.api.service.progress.XpService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación de la lógica
 * de negocio relacionada
 * con actividades.
 */
@Service
@RequiredArgsConstructor
public class ActivityServiceImpl
        implements ActivityService {

    private final ActivityRepository activityRepository;

    private final GoalRepository goalRepository;

    private final ActivityMapper activityMapper;

    private final XpService xpService;

    @Override
    public ActivityResponse createActivity(
            CreateActivityRequest request
    ) {

        GoalEntity goal =
                goalRepository.findById(
                                request.getGoalId()
                        )
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                        "Meta no encontrada"
                                )
                        );

        ActivityEntity activity =
                activityMapper.toEntity(
                        request
                );

        activity.setGoal(
                goal
        );

        ActivityEntity savedActivity =
                activityRepository.save(
                        activity
                );

        return activityMapper.toResponse(
                savedActivity
        );
    }

    @Override
    public ActivityResponse getActivityById(
            String activityId
    ) {

        ActivityEntity activity =
                activityRepository.findById(
                                activityId
                        )
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                        "Actividad no encontrada"
                                )
                        );

        return activityMapper.toResponse(
                activity
        );
    }

    @Override
    public List<ActivityResponse> getActivitiesByGoal(
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

        return activityRepository
                .findAllByGoal(
                        goal
                )
                .stream()
                .map(
                        activityMapper::toResponse
                )
                .toList();
    }

    @Override
    public ActivityResponse updateActivity(
            String activityId,
            UpdateActivityRequest request
    ) {

        ActivityEntity activity =
                activityRepository.findById(
                                activityId
                        )
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                        "Actividad no encontrada"
                                )
                        );


        ActivityStatus previousStatus =
                activity.getStatus();


        activityMapper.updateEntity(
                activity,
                request
        );


        if (

                previousStatus != ActivityStatus.COMPLETED

                        &&

                        request.getStatus()
                                == ActivityStatus.COMPLETED

                        &&

                        !activity.getXpGranted()

        ) {

            xpService.addXp(
                    activity.getGoal()
                            .getUser(),
                    10
            );

            activity.setXpGranted(
                    true
            );
        }


        ActivityEntity updatedActivity =
                activityRepository.save(
                        activity
                );

        return activityMapper.toResponse(
                updatedActivity
        );
    }

    @Override
    public void deleteActivity(
            String activityId
    ) {

        ActivityEntity activity =
                activityRepository.findById(
                                activityId
                        )
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                        "Actividad no encontrada"
                                )
                        );

        activityRepository.delete(
                activity
        );
    }
}