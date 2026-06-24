package com.everpath.domain.usecase.quest

import com.everpath.domain.model.Quest
import com.everpath.domain.repository.QuestRepository
import kotlinx.coroutines.flow.Flow

class GetQuestsUseCase(
    private val questRepository: QuestRepository
) {

    operator fun invoke(): Flow<List<Quest>> {
        return questRepository.getQuests()
    }
}