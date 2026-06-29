package com.everpath.data.local.datasource

import com.everpath.data.local.dao.UserProgressDao
import com.everpath.data.local.entity.UserProgressEntity
import com.everpath.data.session.UserSession
import kotlinx.coroutines.flow.Flow

/**
 * DataSource encargado de encapsular todas las
 * operaciones locales relacionadas con el progreso
 * global del usuario.
 *
 * Su responsabilidad es actuar como intermediario
 * entre los repositorios y Room, evitando que el
 * resto de la aplicación dependa directamente
 * de UserProgressDao.
 */
class UserProgressLocalDataSource(
    private val userProgressDao: UserProgressDao
) {


    fun getUserProgress():
            Flow<UserProgressEntity?> {

        return userProgressDao.getUserProgress(
            UserSession.userId
        )
    }


    suspend fun getCurrentUserProgress():
            UserProgressEntity? {

        return userProgressDao
            .getCurrentUserProgress(
                UserSession.userId
            )
    }


    suspend fun saveUserProgress(
        userProgress: UserProgressEntity
    ) {

        userProgressDao.insertUserProgress(
            userProgress
        )
    }


    suspend fun updateUserProgress(
        userProgress: UserProgressEntity
    ) {

        userProgressDao.updateUserProgress(
            userProgress
        )
    }

}