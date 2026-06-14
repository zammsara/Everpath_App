package com.everpath.di

import android.content.Context
import androidx.room.Room
import com.everpath.data.local.database.EverpathDatabase
import com.everpath.data.repository.GoalRepositoryImpl
import com.everpath.domain.repository.GoalRepository
import com.everpath.domain.usecase.goal.DeleteGoalNodeUseCase
import com.everpath.domain.usecase.goal.GetGoalNodeByIdUseCase
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase
import com.everpath.domain.usecase.goal.SaveGoalNodeUseCase
import com.everpath.data.repository.GoalPositionRepositoryImpl
import com.everpath.domain.repository.GoalPositionRepository
import com.everpath.domain.usecase.goal.UpdateGoalNodeUseCase
import com.everpath.domain.usecase.goalposition.*

class AppContainer(
    context: Context
) {

    private val database: EverpathDatabase =
        Room.databaseBuilder(
            context,
            EverpathDatabase::class.java,
            "everpath_database"
        ).build()

    private val goalRepository: GoalRepository =
        GoalRepositoryImpl(
            goalDao = database.goalDao()
        )

    private val goalPositionRepository:
            GoalPositionRepository =

        GoalPositionRepositoryImpl(
            goalPositionDao =
                database.goalPositionDao()
        )

    val getGoalNodesUseCase =
        GetGoalNodesUseCase(goalRepository)

    val getGoalNodeByIdUseCase =
        GetGoalNodeByIdUseCase(goalRepository)

    val saveGoalNodeUseCase =
        SaveGoalNodeUseCase(goalRepository)

    val deleteGoalNodeUseCase =
        DeleteGoalNodeUseCase(goalRepository)

    val getGoalPositionsUseCase =
        GetGoalPositionsUseCase(
            goalPositionRepository
        )

    val getGoalPositionByIdUseCase =
        GetGoalPositionByIdUseCase(
            goalPositionRepository
        )

    val saveGoalPositionUseCase =
        SaveGoalPositionUseCase(
            goalPositionRepository
        )

    val updateGoalNodeUseCase =
        UpdateGoalNodeUseCase(
            goalRepository
        )

    val deleteGoalPositionUseCase =
        DeleteGoalPositionUseCase(
            goalPositionRepository
        )
}