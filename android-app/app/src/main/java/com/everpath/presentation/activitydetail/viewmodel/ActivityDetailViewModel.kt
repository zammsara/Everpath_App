package com.everpath.presentation.activitydetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everpath.domain.enums.ActivityStatus
import com.everpath.domain.model.Activity
import com.everpath.domain.usecase.activity.DeleteActivityUseCase
import com.everpath.domain.usecase.activity.GetActivityByIdUseCase
import com.everpath.domain.usecase.activity.UpdateActivityUseCase
import com.everpath.domain.usecase.activity.CompleteActivityUseCase
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
    private val completeActivityUseCase: CompleteActivityUseCase,
    private val deleteActivityUseCase: DeleteActivityUseCase
) : ViewModel() {

    private val _activity =
        MutableStateFlow<Activity?>(null)

    val activity: StateFlow<Activity?> =
        _activity

    fun loadActivity(
        activityId: String
    ) {

        viewModelScope.launch {

            _activity.value =
                getActivityByIdUseCase(
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

        viewModelScope.launch {
            if (
                currentActivity.status != ActivityStatus.COMPLETED &&
                status == ActivityStatus.COMPLETED
            ) {
                completeActivityUseCase(
                    currentActivity
                )

            } else {
                updateActivityUseCase(
                    updatedActivity
                )
            }

            _activity.value =
                getActivityByIdUseCase(
                    currentActivity.id
                )
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