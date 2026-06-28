package com.everpath.presentation.activity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everpath.domain.enums.ActivityStatus
import com.everpath.domain.model.Activity
import com.everpath.domain.usecase.activity.DeleteActivityUseCase
import com.everpath.domain.usecase.activity.GetActivitiesByGoalIdUseCase
import com.everpath.domain.usecase.activity.SaveActivityUseCase
import com.everpath.domain.usecase.activity.UpdateActivityUseCase
import com.everpath.presentation.activity.state.ActivityUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class ActivityViewModel(
    private val getActivitiesByGoalIdUseCase: GetActivitiesByGoalIdUseCase,
    private val saveActivityUseCase: SaveActivityUseCase,
    private val updateActivityUseCase: UpdateActivityUseCase,
    private val deleteActivityUseCase: DeleteActivityUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            ActivityUiState()
        )

    val uiState: StateFlow<ActivityUiState> =
        _uiState.asStateFlow()

    fun loadActivities(
        goalId: String
    ) {

        viewModelScope.launch {
            getActivitiesByGoalIdUseCase(
                goalId
            ).collect { activities ->

                _uiState.update {
                    it.copy(
                        activities = activities,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun createActivity(
        goalId: String,
        title: String,
        description: String
    ) {

        val activity =
            Activity(
                id = UUID.randomUUID().toString(),
                goalId = goalId,
                title = title,
                description = description,
                status = ActivityStatus.PENDING
            )

        viewModelScope.launch {

            saveActivityUseCase(
                activity
            )

        }

    }

    fun updateActivity(
        title: String,
        description: String
    ) {

        val selectedActivity =
            _uiState.value.activities
                .find {
                    it.id ==
                            _uiState.value.selectedActivityId
                }
                ?: return

        val updatedActivity =
            selectedActivity.copy(
                title = title,
                description = description
            )

        viewModelScope.launch {

            updateActivityUseCase(
                updatedActivity
            )

        }

    }

    fun deleteSelectedActivity() {

        val activityId =
            _uiState.value.selectedActivityId
                ?: return

        viewModelScope.launch {

            deleteActivityUseCase(
                activityId
            )

            _uiState.update {

                it.copy(
                    selectedActivityId = null
                )

            }

        }

    }

    fun selectActivity(
        activityId: String
    ) {

        _uiState.update {

            it.copy(
                selectedActivityId = activityId
            )

        }

    }

}