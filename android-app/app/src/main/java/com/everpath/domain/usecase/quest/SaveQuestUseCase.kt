package com.everpath.domain.usecase.quest

import com.everpath.domain.model.Quest
import com.everpath.domain.repository.QuestRepository

class SaveQuestUseCase(
    private val questRepository: QuestRepository
) {

    suspend operator fun invoke(quest: Quest) {
        questRepository.saveQuest(quest)
    }
}