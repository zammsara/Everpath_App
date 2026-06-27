package com.everpath.data.sync

import com.everpath.data.session.UserSession
import com.everpath.domain.repository.GoalRepository
import com.everpath.domain.sync.SyncManager

/**
 * Implementación principal del mecanismo
 * de sincronización Offline First.
 *
 * Centraliza todas las descargas desde el
 * backend hacia la base de datos local.
 */
class SyncManagerImpl(

    private val goalRepository: GoalRepository

) : SyncManager {

    /**
     * Ejecuta una sincronización completa
     * del usuario actual.
     */
    override suspend fun refresh() {

        goalRepository.fetchGoals(
            UserSession.userId
        )

    }

}