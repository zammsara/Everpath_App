package com.everpath.presentation.activitydetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everpath.domain.usecase.activity.CompleteActivityUseCase
import com.everpath.domain.usecase.activity.DeleteActivityUseCase
import com.everpath.domain.usecase.activity.FetchActivityByIdUseCase
import com.everpath.domain.usecase.activity.GetActivityByIdUseCase
import com.everpath.domain.usecase.activity.UpdateActivityUseCase
/**
 * Factory encargada de crear
 * instancias de ActivityDetailViewModel.
 */
class ActivityDetailViewModelFactory(

    private val getActivityByIdUseCase: GetActivityByIdUseCase,
    private val updateActivityUseCase: UpdateActivityUseCase,
    private val completeActivityUseCase: CompleteActivityUseCase,
    private val deleteActivityUseCase: DeleteActivityUseCase,
    private val fetchActivityByIdUseCase: FetchActivityByIdUseCase

) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        return ActivityDetailViewModel(

            getActivityByIdUseCase =
                getActivityByIdUseCase,

            updateActivityUseCase =
                updateActivityUseCase,

            completeActivityUseCase =
                completeActivityUseCase,

            deleteActivityUseCase =
                deleteActivityUseCase,

            fetchActivityByIdUseCase =
                fetchActivityByIdUseCase

        ) as T
    }
}