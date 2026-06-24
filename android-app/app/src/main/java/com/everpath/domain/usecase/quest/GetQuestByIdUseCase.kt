package com.everpath.domain.usecase.quest

import com.everpath.domain.model.Quest
import com.everpath.domain.repository.QuestRepository

class GetQuestByIdUseCase(
    private val questRepository: QuestRepository
) {

    suspend operator fun invoke(id: String): Quest? {
        return questRepository.getQuestById(id)
    }
}