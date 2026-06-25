package com.everpath.api.service.goal;

import com.everpath.api.dto.goal.CreateGoalRequest;
import com.everpath.api.dto.goal.GoalResponse;
import com.everpath.api.dto.goal.UpdateGoalRequest;

import java.util.List;

/**
 * Contrato encargado de definir
 * las operaciones de negocio
 * relacionadas con metas.
 */
public interface GoalService {

    GoalResponse createGoal(
            CreateGoalRequest request
    );

    GoalResponse getGoalById(
            String goalId
    );

    List<GoalResponse> getGoalsByUser(
            Long userId
    );

    GoalResponse updateGoal(
            String goalId,
            UpdateGoalRequest request
    );

    void deleteGoal(
            String goalId
    );

}