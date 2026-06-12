package com.everpath.presentation.everpath.preview

import com.everpath.domain.enums.GoalStatus
import com.everpath.domain.enums.LifeAreaType
import com.everpath.domain.model.GoalNode

object EverpathMockData {

    val goalNodes = listOf(

        GoalNode(
            id = "goal_1",
            title = "Aprender Kotlin",
            description = "Fundamentos del lenguaje Kotlin",
            lifeArea = LifeAreaType.CAREER,
            status = GoalStatus.ACTIVE,
            activities = emptyList()
        ),

        GoalNode(
            id = "goal_2",
            title = "Aprender Compose",
            description = "Construcción de interfaces modernas",
            lifeArea = LifeAreaType.CAREER,
            status = GoalStatus.ACTIVE,
            activities = emptyList()
        ),

        GoalNode(
            id = "goal_3",
            title = "Crear Portafolio",
            description = "Proyecto para mostrar habilidades",
            lifeArea = LifeAreaType.CAREER,
            status = GoalStatus.LOCKED,
            activities = emptyList()
        )

    )
}