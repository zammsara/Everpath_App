package com.everpath.domain.repository

import com.everpath.domain.model.Quest
import kotlinx.coroutines.flow.Flow

interface QuestRepository {

    fun getQuests(): Flow<List<Quest>>

    suspend fun getQuestById(id: String): Quest?

    suspend fun saveQuest(quest: Quest)

    suspend fun deleteQuest(id: String)
}