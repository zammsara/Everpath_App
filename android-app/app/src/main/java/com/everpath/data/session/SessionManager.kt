package com.everpath.data.session

import android.content.Context
import com.everpath.domain.model.User

/**
 * Administrador encargado de persistir
 * y recuperar la sesión autenticada
 * del usuario.
 *
 * Actualmente utiliza SharedPreferences
 * debido a que únicamente se requiere
 * almacenar información básica del
 * usuario autenticado.
 */
class SessionManager(
    context: Context

) {
    private val sharedPreferences =
        context.getSharedPreferences(
            PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )


    fun saveSession(
        user: User
    ) {

        sharedPreferences.edit()
            .putLong(
                KEY_USER_ID,
                user.id
            )
            .putString(
                KEY_USER_NAME,
                user.name
            )
            .putString(
                KEY_USER_EMAIL,
                user.email
            )
            .apply()

    }


    fun getSession():
            User? {

        val userId =
            sharedPreferences.getLong(
                KEY_USER_ID,
                -1L
            )

        if (userId == -1L) {
            return null
        }

        val name =
            sharedPreferences.getString(
                KEY_USER_NAME,
                null
            ) ?: return null

        val email =
            sharedPreferences.getString(
                KEY_USER_EMAIL,
                null
            ) ?: return null

        return User(
            id = userId,
            name = name,
            email = email
        )
    }


    fun hasSession():
            Boolean {

        return sharedPreferences.contains(
            KEY_USER_ID
        )

    }


    fun clearSession() {

        sharedPreferences.edit()
            .clear()
            .apply()

    }

    companion object {

        private const val
                PREFERENCES_NAME =
            "everpath_session"

        private const val
                KEY_USER_ID =
            "user_id"

        private const val
                KEY_USER_NAME =
            "user_name"

        private const val
                KEY_USER_EMAIL =
            "user_email"

    }
}