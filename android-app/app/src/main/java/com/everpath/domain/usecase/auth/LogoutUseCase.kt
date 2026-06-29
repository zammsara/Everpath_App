package com.everpath.domain.usecase.auth

import com.everpath.data.session.SessionManager
import com.everpath.data.session.UserSession

/**
 * Caso de uso encargado de cerrar
 * la sesión actualmente activa.
 *
 * Elimina la sesión persistida y
 * limpia el estado en memoria para
 * evitar que la aplicación continúe
 * utilizando información de un
 * usuario previamente autenticado.
 */
class LogoutUseCase(
    private val sessionManager: SessionManager

) {

    operator fun invoke() {
        sessionManager.clearSession()
        UserSession.clear()
    }
}