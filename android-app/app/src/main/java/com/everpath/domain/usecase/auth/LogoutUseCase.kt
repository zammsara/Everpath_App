package com.everpath.domain.usecase.auth

import com.everpath.data.session.SessionManager
import com.everpath.data.session.UserSession
import com.everpath.domain.usecase.database.ClearLocalDatabaseUseCase

/**
 * Caso de uso encargado de cerrar
 * la sesión actualmente activa.
 *
 * Además elimina toda la información
 * almacenada localmente para evitar
 * contaminación entre usuarios.
 */
class LogoutUseCase(
    private val sessionManager: SessionManager,
    private val clearLocalDatabaseUseCase: ClearLocalDatabaseUseCase

) {

    suspend operator fun invoke() {
        clearLocalDatabaseUseCase()
        sessionManager.clearSession()
        UserSession.clear()

    }
}