package com.everpath.presentation.activitydetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everpath.domain.usecase.activity.DeleteActivityUseCase
import com.everpath.domain.usecase.activity.GetActivityByIdUseCase
import com.everpath.domain.usecase.activity.UpdateActivityUseCase
import com.everpath.domain.usecase.activity.CompleteActivityUseCase

class ActivityDetailViewModelFactory(
    private val getActivityByIdUseCase:GetActivityByIdUseCase,
    private val updateActivityUseCase: UpdateActivityUseCase,
    private val completeActivityUseCase: CompleteActivityUseCase,
    private val deleteActivityUseCase: DeleteActivityUseCase

) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        return ActivityDetailViewModel(
            getActivityByIdUseCase = getActivityByIdUseCase,
            updateActivityUseCase = updateActivityUseCase,
            completeActivityUseCase = completeActivityUseCase,
            deleteActivityUseCase = deleteActivityUseCase

        ) as T

    }

}