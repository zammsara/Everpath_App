package com.everpath.domain.model

import com.everpath.domain.enums.LifeAreaType
import com.everpath.domain.enums.QuestStatus
import com.everpath.domain.enums.QuestType

data class Quest(
    val id: String,
    val title: String,
    val description: String,
    val lifeArea: LifeAreaType,
    val type: QuestType,
    val status: QuestStatus
)