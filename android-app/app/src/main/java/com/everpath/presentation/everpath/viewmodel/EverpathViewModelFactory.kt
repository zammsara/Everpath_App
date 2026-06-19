package com.everpath.presentation.everpath.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everpath.domain.usecase.goal.DeleteGoalNodeUseCase
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase
import com.everpath.domain.usecase.goal.SaveGoalNodeUseCase
import com.everpath.domain.usecase.goal.UpdateGoalNodeUseCase
import com.everpath.domain.usecase.goalposition.GetGoalPositionsUseCase
import com.everpath.domain.usecase.goalposition.SaveGoalPositionUseCase
import com.everpath.domain.usecase.goalconnection.GetGoalConnectionsUseCase
import com.everpath.domain.usecase.goalconnection.SaveGoalConnectionUseCase
import com.everpath.domain.usecase.goalconnection.DeleteGoalConnectionUseCase

/**
 * Factory encargada de crear instancias de EverpathViewModel.
 *
 * Su responsabilidad es proporcionar todas las dependencias
 * necesarias para el ViewModel sin acoplar la UI a la capa
 * de dominio o a la configuración de inyección.
 */
class EverpathViewModelFactory(
    private val getGoalNodesUseCase: GetGoalNodesUseCase,
    private val saveGoalNodeUseCase: SaveGoalNodeUseCase,
    private val updateGoalNodeUseCase: UpdateGoalNodeUseCase,
    private val saveGoalPositionUseCase: SaveGoalPositionUseCase,
    private val getGoalPositionsUseCase: GetGoalPositionsUseCase,
    private val deleteGoalNodeUseCase: DeleteGoalNodeUseCase,
    private val getGoalConnectionsUseCase: GetGoalConnectionsUseCase,
    private val saveGoalConnectionUseCase: SaveGoalConnectionUseCase,
    private val deleteGoalConnectionUseCase: DeleteGoalConnectionUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (
            modelClass.isAssignableFrom(
                EverpathViewModel::class.java
            )
        ) {

            return EverpathViewModel(
                getGoalNodesUseCase = getGoalNodesUseCase,
                saveGoalNodeUseCase = saveGoalNodeUseCase,
                updateGoalNodeUseCase = updateGoalNodeUseCase,
                saveGoalPositionUseCase = saveGoalPositionUseCase,
                getGoalPositionsUseCase = getGoalPositionsUseCase,
                deleteGoalNodeUseCase = deleteGoalNodeUseCase,
                getGoalConnectionsUseCase = getGoalConnectionsUseCase,
                saveGoalConnectionUseCase = saveGoalConnectionUseCase,
                deleteGoalConnectionUseCase = deleteGoalConnectionUseCase
            ) as T

        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}