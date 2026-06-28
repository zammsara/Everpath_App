package com.everpath.presentation.activitydetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everpath.domain.usecase.achievement.FetchAchievementsUseCase
import com.everpath.domain.usecase.activity.DeleteActivityUseCase
import com.everpath.domain.usecase.activity.FetchActivityByIdUseCase
import com.everpath.domain.usecase.activity.GetActivityByIdUseCase
import com.everpath.domain.usecase.activity.UpdateActivityUseCase
import com.everpath.domain.usecase.userprogress.FetchUserProgressUseCase

/**
 * Factory encargada de crear
 * instancias de ActivityDetailViewModel.
 */
class ActivityDetailViewModelFactory(

    private val getActivityByIdUseCase:
    GetActivityByIdUseCase,

    private val updateActivityUseCase:
    UpdateActivityUseCase,

    private val deleteActivityUseCase:
    DeleteActivityUseCase,

    private val fetchUserProgressUseCase:
    FetchUserProgressUseCase,

    private val fetchAchievementsUseCase:
    FetchAchievementsUseCase,

    private val fetchActivityByIdUseCase:
    FetchActivityByIdUseCase

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

            deleteActivityUseCase =
                deleteActivityUseCase,

            fetchUserProgressUseCase =
                fetchUserProgressUseCase,

            fetchAchievementsUseCase =
                fetchAchievementsUseCase,

            fetchActivityByIdUseCase =
                fetchActivityByIdUseCase

        ) as T
    }
}