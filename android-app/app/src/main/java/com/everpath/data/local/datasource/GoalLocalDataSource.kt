package com.everpath.data.local.datasource

import com.everpath.data.local.dao.GoalDao
import com.everpath.data.local.entity.GoalEntity
import com.everpath.data.local.relation.GoalWithActivities
import com.everpath.data.session.UserSession
import kotlinx.coroutines.flow.Flow

/**
 * Local Data Source encargado de encapsular
 * todas las operaciones relacionadas con
 * metas almacenadas mediante Room.
 *
 * Esta clase actúa como intermediario entre
 * los repositorios y los DAO, evitando que
 * la capa de repositorios dependa
 * directamente de Room.
 *
 * En etapas posteriores coexistirá con
 * GoalRemoteDataSource para implementar
 * una estrategia offline-first.
 */
class GoalLocalDataSource(

    private val goalDao: GoalDao

) {


    fun getAllGoals():
            Flow<List<GoalWithActivities>> {

        return goalDao.getAllGoals(
            UserSession.userId
        )

    }


    fun getGoalById(
        goalId: String
    ): Flow<GoalWithActivities?> {

        return goalDao.getGoalById(
            goalId = goalId,
            userId = UserSession.userId
        )

    }

    /**
     * Inserta una meta
     * dentro de Room.
     */
    suspend fun insertGoal(
        goal: GoalEntity
    ) {

        goalDao.insertGoal(goal)

    }


    suspend fun updateGoal(
        goal: GoalEntity
    ) {

        goalDao.updateGoal(goal)

    }


    suspend fun deleteGoalById(
        goalId: String
    ) {

        goalDao.deleteGoalById(
            goalId
        )

    }

}