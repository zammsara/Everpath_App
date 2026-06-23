package com.everpath.api.service;

import com.everpath.api.dto.CreateGoalRequest;
import com.everpath.api.dto.GoalResponse;
import com.everpath.api.dto.UpdateGoalRequest;

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