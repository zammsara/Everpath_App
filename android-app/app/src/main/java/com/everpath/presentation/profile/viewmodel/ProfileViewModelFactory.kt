package com.everpath.presentation.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase
import com.everpath.domain.usecase.userprogress.GetUserProgressUseCase

/**
 * Factory encargada de crear
 * instancias de ProfileViewModel.
 */
class ProfileViewModelFactory(
    private val getGoalNodesUseCase: GetGoalNodesUseCase,
    private val getUserProgressUseCase: GetUserProgressUseCase

) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (
            modelClass.isAssignableFrom(
                ProfileViewModel::class.java
            )
        ) {

            return ProfileViewModel(
                getGoalNodesUseCase =
                    getGoalNodesUseCase,
                getUserProgressUseCase =
                    getUserProgressUseCase
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}