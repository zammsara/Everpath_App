package com.everpath.presentation.quest.state

import com.everpath.domain.model.GoalNode

/**
 * Estado principal de QuestScreen.
 *
 * Contiene la información necesaria
 * para representar las metas activas
 * que funcionarán como misiones.
 */
data class QuestUiState(

    val activeGoals: List<GoalNode> = emptyList(),

    val completedGoals: Int = 0,

    val isLoading: Boolean = true

)