package com.everpath.presentation.activity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everpath.domain.usecase.activity.DeleteActivityUseCase
import com.everpath.domain.usecase.activity.FetchActivitiesByGoalUseCase
import com.everpath.domain.usecase.activity.GetActivitiesByGoalIdUseCase
import com.everpath.domain.usecase.activity.SaveActivityUseCase
import com.everpath.domain.usecase.activity.UpdateActivityUseCase

class ActivityViewModelFactory(

    private val getActivitiesByGoalIdUseCase:
    GetActivitiesByGoalIdUseCase,

    private val fetchActivitiesByGoalUseCase:
    FetchActivitiesByGoalUseCase,

    private val saveActivityUseCase:
    SaveActivityUseCase,

    private val updateActivityUseCase:
    UpdateActivityUseCase,

    private val deleteActivityUseCase:
    DeleteActivityUseCase

) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        return ActivityViewModel(

            getActivitiesByGoalIdUseCase =
                getActivitiesByGoalIdUseCase,

            fetchActivitiesByGoalUseCase =
                fetchActivitiesByGoalUseCase,

            saveActivityUseCase =
                saveActivityUseCase,

            updateActivityUseCase =
                updateActivityUseCase,

            deleteActivityUseCase =
                deleteActivityUseCase

        ) as T

    }

}