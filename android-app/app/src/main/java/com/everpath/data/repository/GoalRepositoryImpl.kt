package com.everpath.data.repository

import com.everpath.data.local.dao.GoalDao
import com.everpath.data.local.mapper.toDomain
import com.everpath.data.local.mapper.toEntity
import com.everpath.data.remote.datasource.GoalRemoteDataSource
import com.everpath.data.remote.mapper.toCreateRequestDto
import com.everpath.data.remote.mapper.toDomain
import com.everpath.data.remote.mapper.toUpdateRequestDto
import com.everpath.domain.model.GoalNode
import com.everpath.domain.repository.GoalPositionRepository
import com.everpath.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Repositorio encargado de coordinar la
 * persistencia local mediante Room y la
 * comunicación remota mediante Retrofit.
 *
 * Implementa la estrategia Offline First,
 * donde la UI observa exclusivamente la
 * base de datos local mientras el repositorio
 * sincroniza la información con el backend.
 */
class GoalRepositoryImpl(

    private val goalDao: GoalDao,
    private val goalRemoteDataSource: GoalRemoteDataSource,
    private val goalPositionRepository: GoalPositionRepository

) : GoalRepository {

    /**
     * Observa continuamente las metas
     * almacenadas en Room.
     */
    override fun observeGoals(): Flow<List<GoalNode>> {

        return goalDao
            .getAllGoals()
            .map { goals ->

                goals.map {
                    it.toDomain()
                }
            }
    }

    /**
     * Observa una meta específica
     * almacenada localmente.
     */
    override fun observeGoalById(
        goalId: String
    ): Flow<GoalNode?> {

        return goalDao
            .getGoalById(goalId)
            .map { goal ->

                goal?.toDomain()

            }
    }

    /**
     * Descarga todas las metas del
     * backend y reemplaza completamente
     * la información almacenada en Room.
     */
    override suspend fun fetchGoals(
        userId: Long
    ) {

        val remoteGoals =
            goalRemoteDataSource
                .getGoalsByUser(userId)

        val entities =
            remoteGoals
                .map {
                    it.toDomain()
                        .toEntity()
                }

        goalDao.replaceGoals(
            entities
        )
    }

    /**
     * Descarga una meta específica
     * desde el backend y actualiza
     * únicamente dicho registro local.
     */
    override suspend fun fetchGoalById(
        goalId: String
    ) {

        val remoteGoal =
            goalRemoteDataSource
                .getGoalById(goalId)

        goalDao.upsertGoal(
            remoteGoal
                .toDomain()
                .toEntity()
        )
    }

    /**
     * Crea una nueva meta en el servidor
     * y actualiza posteriormente Room con
     * la respuesta oficial del backend.
     */
    override suspend fun createGoal(
        goalNode: GoalNode,
        userId: Long
    ) {

        val remoteGoal =
            goalRemoteDataSource
                .createGoal(
                    goalNode.toCreateRequestDto(
                        userId
                    )
                )

        goalDao.upsertGoal(
            remoteGoal
                .toDomain()
                .toEntity()
        )
    }

    /**
     * Actualiza una meta en el backend
     * y sincroniza posteriormente la
     * información local.
     */
    override suspend fun updateGoal(
        goalNode: GoalNode
    ) {

        val remoteGoal =
            goalRemoteDataSource
                .updateGoal(
                    goalNode.id,
                    goalNode.toUpdateRequestDto()
                )

        goalDao.upsertGoal(
            remoteGoal
                .toDomain()
                .toEntity()
        )
    }

    /**
     * Elimina la meta del backend y,
     * posteriormente, de la base local.
     */
    override suspend fun deleteGoal(
        goalId: String
    ) {

        goalRemoteDataSource
            .deleteGoal(goalId)

        goalDao.deleteGoalById(
            goalId
        )
    }
}