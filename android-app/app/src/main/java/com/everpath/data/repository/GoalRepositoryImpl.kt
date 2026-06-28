package com.everpath.data.repository

import android.util.Log
import com.everpath.data.local.dao.GoalDao
import com.everpath.data.local.entity.GoalPositionEntity
import com.everpath.data.local.mapper.toDomain
import com.everpath.data.local.mapper.toEntity
import com.everpath.data.remote.datasource.GoalRemoteDataSource
import com.everpath.data.remote.mapper.toCreateRequestDto
import com.everpath.data.remote.mapper.toDomain
import com.everpath.data.remote.mapper.toUpdateRequestDto
import com.everpath.data.remote.util.safeApiCall
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


    private suspend fun createDefaultPositionIfNeeded(
        goalId: String,
        x: Float,
        y: Float
    ) {

        val existingPosition =
            goalPositionRepository
                .getGoalPositionById(
                    goalId
                )

        if (existingPosition == null) {

            goalPositionRepository
                .saveGoalPosition(
                    GoalPositionEntity(
                        goalId = goalId,
                        x = x,
                        y = y
                    )
                )
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

        val result =
            safeApiCall(
                tag = "GoalRepository"
            ) {
                goalRemoteDataSource
                    .getGoalsByUser(userId)
            }

        if (
            result.isFailure
        ) {

            Log.i(
                "GoalRepository",
                "Sincronización omitida. Se utilizarán los datos almacenados en Room."
            )

            return

        }

        val remoteGoals =
            result.getOrThrow()

        val entities =
            remoteGoals
                .map {
                    it.toDomain()
                        .toEntity()
                }

        goalDao.upsertGoals(
            entities
        )

        remoteGoals.forEachIndexed { index, goal ->

            createDefaultPositionIfNeeded(
                goalId = goal.id,
                x = 100f + (index * 250f),
                y = 100f
            )
        }
    }

    /**
     * Descarga una meta específica
     * desde el backend y actualiza
     * únicamente dicho registro local.
     */
    override suspend fun fetchGoalById(
        goalId: String
    ) {

        val result =
            safeApiCall(
                tag = "GoalRepository"
            ) {
                goalRemoteDataSource
                    .getGoalById(goalId)
            }

        result.getOrNull()?.let { remoteGoal ->

            goalDao.upsertGoal(
                remoteGoal
                    .toDomain()
                    .toEntity()
            )

        }
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

        val result =
            safeApiCall(
                tag = "GoalRepository"
            ) {
                goalRemoteDataSource.createGoal(
                    goalNode.toCreateRequestDto(
                        userId
                    )
                )
            }

        val remoteGoal =
            result.getOrElse {

                Log.i(
                    "GoalRepository",
                    "No fue posible crear la meta en el servidor."
                )

                return

            }

        val savedGoal =
            remoteGoal.toDomain()

        goalDao.upsertGoal(
            savedGoal.toEntity()
        )

        createDefaultPositionIfNeeded(
            goalId = savedGoal.id,
            x = 100f,
            y = 100f
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

        val result =
            safeApiCall(
                tag = "GoalRepository"
            ) {
                goalRemoteDataSource.updateGoal(
                    goalNode.id,
                    goalNode.toUpdateRequestDto()
                )
            }

        val remoteGoal =
            result.getOrElse {

                Log.i(
                    "GoalRepository",
                    "No fue posible actualizar la meta en el servidor."
                )

                return

            }

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

        val result =
            safeApiCall(
                tag = "GoalRepository"
            ) {
                goalRemoteDataSource
                    .deleteGoal(goalId)
            }

        if (
            result.isFailure
        ) {

            Log.i(
                "GoalRepository",
                "No fue posible eliminar la meta del servidor."
            )

            return

        }

        goalDao.deleteGoalById(goalId)
    }
}