package com.everpath.presentation.everpath.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everpath.data.local.entity.GoalPositionEntity
import com.everpath.domain.enums.GoalStatus
import com.everpath.domain.enums.LifeAreaType
import com.everpath.domain.model.GoalConnection
import com.everpath.domain.model.GoalNode
import com.everpath.domain.usecase.goal.DeleteGoalNodeUseCase
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase
import com.everpath.domain.usecase.goal.SaveGoalNodeUseCase
import com.everpath.domain.usecase.goal.UpdateGoalNodeUseCase
import com.everpath.domain.usecase.goalposition.GetGoalPositionsUseCase
import com.everpath.domain.usecase.goalposition.SaveGoalPositionUseCase
import com.everpath.presentation.everpath.model.GoalNodePosition
import com.everpath.presentation.everpath.state.EverpathUiState
import com.everpath.domain.usecase.goalconnection.GetGoalConnectionsUseCase
import com.everpath.domain.usecase.goalconnection.SaveGoalConnectionUseCase
import com.everpath.domain.usecase.goalconnection.DeleteGoalConnectionUseCase
import com.everpath.domain.usecase.goalposition.UpdateGoalPositionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID


/**
 * ViewModel principal del mapa Everpath.
 *
 * Es responsable de coordinar la comunicación entre
 * la capa de presentación y los casos de uso del dominio.
 *
 * Mantiene actualizado el EverpathUiState que consume
 * la interfaz de usuario.
 */
class EverpathViewModel(
    private val getGoalNodesUseCase: GetGoalNodesUseCase,
    private val saveGoalNodeUseCase: SaveGoalNodeUseCase,
    private val saveGoalPositionUseCase: SaveGoalPositionUseCase,
    private val updateGoalPositionUseCase: UpdateGoalPositionUseCase,
    private val updateGoalNodeUseCase: UpdateGoalNodeUseCase,
    private val getGoalPositionsUseCase: GetGoalPositionsUseCase,
    private val deleteGoalNodeUseCase: DeleteGoalNodeUseCase,
    private val getGoalConnectionsUseCase: GetGoalConnectionsUseCase,
    private val saveGoalConnectionUseCase: SaveGoalConnectionUseCase,
    private val deleteGoalConnectionUseCase: DeleteGoalConnectionUseCase
) : ViewModel() {


    private val _uiState =
        MutableStateFlow(
            EverpathUiState(
                isLoading = true
            )
        )

    val uiState: StateFlow<EverpathUiState> =
        _uiState.asStateFlow()

    init {
        observeGoals()
        observePositions()
        observeConnections()
    }

    private fun observeGoals() {
        viewModelScope.launch {
            getGoalNodesUseCase()
                .collect { goals ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            goalNodes = goals,
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun observePositions() {
        viewModelScope.launch {
            getGoalPositionsUseCase()
                .collect { positions ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            positions = positions.map {
                                GoalNodePosition(
                                    goalNodeId = it.goalId,
                                    x = it.x,
                                    y = it.y
                                )
                            }
                        )
                    }
                }
        }
    }

   private fun observeConnections() {

        viewModelScope.launch {

            getGoalConnectionsUseCase()
                .collect { connections ->

                    _uiState.update {
                        it.copy(
                            connections = connections
                        )
                    }
                }
        }
    }

    fun updateGoalPosition(
        goalId: String,
        x: Float,
        y: Float
    ) {

        viewModelScope.launch {
            updateGoalPositionUseCase(
                GoalPositionEntity(
                    goalId = goalId,
                    x = x,
                    y = y
                )
            )
        }
    }

    /**
     * Inicia el arrastre temporal de una meta.
     */
    fun startDragging(
        goalId: String
    ) {

        val currentPosition =
            _uiState.value.positions
                .find {
                    it.goalNodeId == goalId
                }
                ?: return

        _uiState.update {

            it.copy(
                draggingPositions =
                    it.draggingPositions +
                            (
                                    goalId to currentPosition
                                    )
            )

        }

    }

    /**
     * Actualiza visualmente la posición mientras
     * el usuario arrastra la tarjeta.
     */
    fun dragGoal(
        goalId: String,
        dragX: Float,
        dragY: Float
    ) {

        val currentPosition =
            _uiState.value.draggingPositions[goalId]
                ?: return

        val newPosition =
            currentPosition.copy(
                x = currentPosition.x + dragX,
                y = currentPosition.y + dragY
            )

        _uiState.update {

            it.copy(
                draggingPositions =
                    it.draggingPositions +
                            (
                                    goalId to newPosition
                                    )
            )

        }

    }

    /**
     * Finaliza el arrastre.
     */
    fun finishDragging(
        goalId: String
    ) {

        val finalPosition =
            _uiState.value.draggingPositions[goalId]
                ?: return

        viewModelScope.launch {

            updateGoalPositionUseCase(
                GoalPositionEntity(
                    goalId = goalId,
                    x = finalPosition.x,
                    y = finalPosition.y
                )
            )

        }

        _uiState.update {

            it.copy(
                draggingPositions =
                    it.draggingPositions - goalId
            )

        }

    }

    fun updateDraggingPosition(
        goalId: String,
        x: Float,
        y: Float
    ) {
        _uiState.update {
            it.copy(
                draggingPositions =
                    it.draggingPositions +
                            (
                                    goalId to
                                            GoalNodePosition(
                                                goalNodeId = goalId,
                                                x = x,
                                                y = y
                                            )
                                    )
            )
        }
    }

   fun clearDraggingPosition(
        goalId: String
    ) {

        _uiState.update {

            it.copy(
                draggingPositions =
                    it.draggingPositions -
                            goalId
            )

        }

    }

    /**
     * ---------------------------------------------------------
     * Viewport
     * ---------------------------------------------------------
     *
     * Gestiona la posición de la cámara virtual del mapa.
     *
     * Las coordenadas reales de las metas nunca cambian.
     * Únicamente cambia el desplazamiento visual aplicado
     * durante el renderizado.
     */

    //Desplaza la cámara del mapa.
    fun moveViewport(
        deltaX: Float,
        deltaY: Float
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                viewportState =
                    currentState.viewportState.copy(
                        offsetX =
                            currentState
                                .viewportState
                                .offsetX + deltaX,
                        offsetY =
                            currentState
                                .viewportState
                                .offsetY + deltaY
                    )
            )
        }

        //Restablece la cámara a su posición inicial.
        fun resetViewport() {

            _uiState.update { currentState ->

                currentState.copy(
                    viewportState =
                        currentState.viewportState.copy(
                            offsetX = 0f,
                            offsetY = 0f
                        )
                )

            }

        }

    }

    fun createGoal(
        title: String,
        description: String
    ) {
        val goalId =
            UUID.randomUUID().toString()

        val goal = GoalNode(
            id = goalId,
            title = title,
            description = description,
            lifeArea = LifeAreaType.HEALTH,
            status = GoalStatus.ACTIVE,
            activities = emptyList(),
            progress = 0f
        )

        val position =
            GoalPositionEntity(
                goalId = goalId,
                x = (
                        100f +
                                (_uiState.value.goalNodes.size * 220f)
                        ),
                y = 100f
            )
        viewModelScope.launch {
            saveGoalNodeUseCase(goal)
            saveGoalPositionUseCase(position)
        }
    }

    fun updateGoal(
        title: String,
        description: String,
        status: GoalStatus
    ) {
        val selectedGoalId =
            _uiState.value.selectedGoalId
                ?: return

        val currentGoal =
            _uiState.value.goalNodes
                .find {
                    it.id == selectedGoalId
                }
                ?: return

        val updatedGoal =
            currentGoal.copy(
                title = title,
                description = description,
                status = status
            )

        viewModelScope.launch {
            updateGoalNodeUseCase(updatedGoal)
        }
    }

    fun deleteSelectedGoal() {
        val goalId =
            _uiState.value.selectedGoalId
                ?: return

        viewModelScope.launch {
            deleteGoalNodeUseCase(goalId)
            _uiState.update {
                it.copy(
                    selectedGoalId = null
                )
            }
        }
    }

    fun selectGoal(
        goalId: String
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedGoalId = goalId
            )
        }

    }

    /**
     * Crea una conexión entre dos metas validando
     * las reglas básicas del mapa de progreso.
     */
    fun createConnection(
        sourceGoalId: String,
        targetGoalId: String
    ) {

        if (sourceGoalId == targetGoalId) {return}

        val existingConnections =
            _uiState.value.connections

        val sourceAlreadyConnected =
            existingConnections.any {

                it.sourceGoalId ==
                        sourceGoalId
            }

        if (sourceAlreadyConnected) {
            return
        }

        val duplicatedConnection =
            existingConnections.any {

                it.sourceGoalId ==
                        sourceGoalId &&
                        it.targetGoalId ==
                        targetGoalId
            }

        if (duplicatedConnection) {return}

        val connection =
            GoalConnection(
                id = UUID.randomUUID().toString(),
                sourceGoalId = sourceGoalId,
                targetGoalId = targetGoalId
            )

        viewModelScope.launch {
            saveGoalConnectionUseCase(connection)
        }
    }

    fun startConnectionMode() {
        val selectedGoalId =
            _uiState.value.selectedGoalId
                ?: return

        _uiState.update {
            it.copy(
                isConnectionMode = true,
                connectionSourceGoalId =
                    selectedGoalId
            )
        }
    }

    fun cancelConnectionMode() {
        _uiState.update {
            it.copy(
                connectionSourceGoalId = null
            )
        }
    }

    fun clearSelection() {
        _uiState.update {

            it.copy(
                selectedGoalId = null,
                selectedConnectionId = null
            )
        }
    }

    fun selectConnection(
        connectionId: String
    ) {
        _uiState.update {
            it.copy(
                selectedConnectionId = connectionId,
                selectedGoalId = null
            )
        }
    }

    fun deleteSelectedConnection() {
        val connectionId =
            _uiState.value.selectedConnectionId
                ?: return

        viewModelScope.launch {
            deleteGoalConnectionUseCase(connectionId)
            _uiState.update {
                it.copy(
                    selectedConnectionId = null
                )
            }
        }
    }

    fun handleGoalSelection(
        goalId: String
    ) {
        val currentState =
            _uiState.value

        if (
            currentState.isConnectionMode
        ) {
            val sourceGoalId =
                currentState.connectionSourceGoalId
                    ?: return
            createConnection(
                sourceGoalId = sourceGoalId,
                targetGoalId = goalId
            )
            _uiState.update {
                it.copy(
                    isConnectionMode = false,
                    connectionSourceGoalId = null,
                    selectedGoalId = null
                )
            }
            return
        }
        selectGoal(goalId)
    }

}