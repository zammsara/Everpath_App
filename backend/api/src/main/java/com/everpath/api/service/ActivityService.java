package com.everpath.api.service;

import com.everpath.api.dto.ActivityResponse;
import com.everpath.api.dto.CreateActivityRequest;
import com.everpath.api.dto.UpdateActivityRequest;

import java.util.List;

/**
 * Contrato encargado de definir
 * las operaciones de negocio
 * relacionadas con actividades.
 */
public interface ActivityService {


    ActivityResponse createActivity(
            CreateActivityRequest request
    );


    ActivityResponse getActivityById(
            String activityId
    );


    List<ActivityResponse> getActivitiesByGoal(
            String goalId
    );


    ActivityResponse updateActivity(
            String activityId,
            UpdateActivityRequest request
    );


    void deleteActivity(
            String activityId
    );
}