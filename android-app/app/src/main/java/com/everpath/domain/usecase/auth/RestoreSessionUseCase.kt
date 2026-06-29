package com.everpath.domain.usecase.auth

import com.everpath.data.session.SessionManager
import com.everpath.data.session.UserSession

/**
 * Caso de uso encargado de restaurar
 * la sesión persistida al iniciar
 * la aplicación.
 *
 * Si existe un usuario almacenado
 * en SessionManager, se actualiza
 * UserSession para que el resto de
 * la arquitectura pueda utilizar
 * el usuario autenticado.
 */
class RestoreSessionUseCase(

    private val sessionManager: SessionManager

) {

    operator fun invoke(): Boolean {

        val user =
            sessionManager.getSession()
                ?: return false

        UserSession.initialize(
            user.id
        )

        return true
    }
}