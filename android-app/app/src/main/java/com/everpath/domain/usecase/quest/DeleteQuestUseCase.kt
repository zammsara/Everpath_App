package com.everpath.domain.usecase.quest

import com.everpath.domain.repository.QuestRepository

class DeleteQuestUseCase(
    private val questRepository: QuestRepository
) {

    suspend operator fun invoke(id: String) {
        questRepository.deleteQuest(id)
    }
}