package com.everpath.presentation.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase

/**
 * Factory encargada de crear
 * instancias de ProfileViewModel.
 */
class ProfileViewModelFactory(

    private val getGoalNodesUseCase:
    GetGoalNodesUseCase

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
                    getGoalNodesUseCase

            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}