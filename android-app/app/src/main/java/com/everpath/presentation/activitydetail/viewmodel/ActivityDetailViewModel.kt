package com.everpath.presentation.activitydetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everpath.data.session.UserSession
import com.everpath.domain.enums.ActivityStatus
import com.everpath.domain.model.Activity
import com.everpath.domain.usecase.achievement.FetchAchievementsUseCase
import com.everpath.domain.usecase.activity.DeleteActivityUseCase
import com.everpath.domain.usecase.activity.FetchActivityByIdUseCase
import com.everpath.domain.usecase.activity.GetActivityByIdUseCase
import com.everpath.domain.usecase.activity.UpdateActivityUseCase
import com.everpath.domain.usecase.userprogress.FetchUserProgressUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar
 * el detalle y edición de actividades.
 */
class ActivityDetailViewModel(
    private val getActivityByIdUseCase: GetActivityByIdUseCase,
    private val updateActivityUseCase: UpdateActivityUseCase,
    private val deleteActivityUseCase: DeleteActivityUseCase,
    private val fetchUserProgressUseCase: FetchUserProgressUseCase,
    private val fetchAchievementsUseCase: FetchAchievementsUseCase,
    private val fetchActivityByIdUseCase: FetchActivityByIdUseCase
) : ViewModel() {

    private val _activity =
        MutableStateFlow<Activity?>(null)

    val activity: StateFlow<Activity?> =
        _activity

    fun loadActivity(
        activityId: String
    ) {
        viewModelScope.launch {

            getActivityByIdUseCase(
                activityId
            ).collect { activity ->

                _activity.value =
                    activity
            }
        }

        viewModelScope.launch {
            fetchActivityByIdUseCase(
                activityId
            )
        }
    }

    fun updateActivity(
        title: String,
        description: String,
        status: ActivityStatus
    ) {

        val currentActivity =
            _activity.value
                ?: return

        val updatedActivity =
            currentActivity.copy(
                title = title,
                description = description,
                status = status
            )

        val wasCompleted =
            currentActivity.status ==
                    ActivityStatus.COMPLETED

        viewModelScope.launch {

            updateActivityUseCase(
                updatedActivity
            )

            if (
                !wasCompleted &&
                status == ActivityStatus.COMPLETED
            ) {

                fetchUserProgressUseCase()

                fetchAchievementsUseCase(
                    UserSession.userId
                )

            }

        }
    }

    fun deleteActivity(
        onDeleted: () -> Unit
    ) {

        val currentActivity =
            _activity.value
                ?: return

        viewModelScope.launch {
            deleteActivityUseCase(
                currentActivity.id
            )
            onDeleted()

        }
    }
}