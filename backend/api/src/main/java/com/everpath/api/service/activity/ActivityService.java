package com.everpath.api.service.activity;

import com.everpath.api.dto.activity.ActivityResponse;
import com.everpath.api.dto.activity.CreateActivityRequest;
import com.everpath.api.dto.activity.UpdateActivityRequest;

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