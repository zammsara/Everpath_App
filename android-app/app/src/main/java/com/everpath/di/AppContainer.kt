package com.everpath.di

import android.content.Context
import androidx.room.Room
import com.everpath.data.local.database.EverpathDatabase
import com.everpath.data.repository.AchievementRepositoryImpl
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
import com.everpath.data.repository.ActivityRepositoryImpl
import com.everpath.domain.repository.ActivityRepository
import com.everpath.domain.usecase.activity.DeleteActivityUseCase
import com.everpath.domain.usecase.activity.GetActivitiesByGoalIdUseCase
import com.everpath.domain.usecase.activity.GetActivityByIdUseCase
import com.everpath.domain.usecase.activity.SaveActivityUseCase
import com.everpath.domain.usecase.activity.UpdateActivityUseCase
import com.everpath.data.repository.GoalConnectionRepositoryImpl
import com.everpath.domain.repository.GoalConnectionRepository
import com.everpath.domain.usecase.goalconnection.DeleteGoalConnectionUseCase
import com.everpath.domain.usecase.goalconnection.GetGoalConnectionsUseCase
import com.everpath.domain.usecase.goalconnection.SaveGoalConnectionUseCase
import com.everpath.data.repository.UserProgressRepositoryImpl
import com.everpath.domain.repository.AchievementRepository
import com.everpath.domain.repository.UserProgressRepository
import com.everpath.domain.usecase.achievement.GetAchievementByIdUseCase
import com.everpath.domain.usecase.achievement.GetAchievementsUseCase
import com.everpath.domain.usecase.userprogress.GetUserProgressUseCase
import com.everpath.domain.usecase.activity.CompleteActivityUseCase
import com.everpath.domain.usecase.goal.CompleteGoalNodeUseCase
import com.everpath.domain.usecase.userprogress.GetLevelProgressUseCase
import com.everpath.domain.usecase.userprogress.GetUserLevelUseCase
import com.everpath.data.remote.datasource.ActivityRemoteDataSource
import com.everpath.data.remote.datasource.AchievementRemoteDataSource
import com.everpath.data.remote.datasource.GoalRemoteDataSource
import com.everpath.data.remote.datasource.UserProgressRemoteDataSource
import com.everpath.data.remote.network.RetrofitProvider
import com.everpath.data.remote.service.ActivityApiService
import com.everpath.data.remote.service.GoalApiService


/**
 * Contenedor principal de dependencias de Everpath.
 *
 * Centraliza la creación de Room, repositorios
 * y casos de uso de la aplicación.
 */
class AppContainer(
    context: Context
) {

    // Base de datos

    private val database: EverpathDatabase =
        Room.databaseBuilder(
            context,
            EverpathDatabase::class.java,
            "everpath_database"
        )
            .fallbackToDestructiveMigration()
            .build()


    // Api Services

    private val goalApiService =
        RetrofitProvider
            .retrofit
            .create(
                GoalApiService::class.java
            )

    private val activityApiService =
        RetrofitProvider
            .retrofit
            .create(
                ActivityApiService::class.java
            )


    // Remote Data Sources

    private val goalRemoteDataSource =
        GoalRemoteDataSource(
            goalApiService
        )

    private val activityRemoteDataSource =
        ActivityRemoteDataSource(
            activityApiService
        )

    private val userProgressRemoteDataSource =
        UserProgressRemoteDataSource()

    private val achievementRemoteDataSource =
        AchievementRemoteDataSource()


    // Repositorios

    private val goalRepository: GoalRepository =
        GoalRepositoryImpl(
            goalDao = database.goalDao(),
            goalRemoteDataSource =
                goalRemoteDataSource
        )

    private val activityRepository:
            ActivityRepository =

        ActivityRepositoryImpl(
            activityDao =
                database.activityDao(),

            remoteDataSource =
                activityRemoteDataSource
        )

    private val goalPositionRepository:
            GoalPositionRepository =

        GoalPositionRepositoryImpl(
            goalPositionDao =
                database.goalPositionDao()
        )

    private val goalConnectionRepository:
            GoalConnectionRepository =

        GoalConnectionRepositoryImpl(
            goalConnectionDao =
                database.goalConnectionDao()
        )


    private val userProgressRepository:
            UserProgressRepository =

        UserProgressRepositoryImpl(
            userProgressDao =
                database.userProgressDao(),

            remoteDataSource =
                userProgressRemoteDataSource
        )
    private val achievementRepository:
            AchievementRepository =

        AchievementRepositoryImpl(
            achievementDao =
                database.achievementDao(),

            achievementRemoteDataSource =
                achievementRemoteDataSource
        )

    // Goals

    val getGoalNodesUseCase =
        GetGoalNodesUseCase(goalRepository)

    val getGoalNodeByIdUseCase =
        GetGoalNodeByIdUseCase(goalRepository)

    val saveGoalNodeUseCase =
        SaveGoalNodeUseCase(goalRepository)

    val updateGoalNodeUseCase =
        UpdateGoalNodeUseCase(goalRepository)

    val deleteGoalNodeUseCase =
        DeleteGoalNodeUseCase(goalRepository)


    // Goal Positions

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

    val updateGoalPositionUseCase =
        UpdateGoalPositionUseCase(
            goalPositionRepository
        )

    val deleteGoalPositionUseCase =
        DeleteGoalPositionUseCase(
            goalPositionRepository
        )

    // Activities

    val getActivitiesByGoalIdUseCase =
        GetActivitiesByGoalIdUseCase(
            activityRepository
        )

    val getActivityByIdUseCase =
        GetActivityByIdUseCase(
            activityRepository
        )

    val saveActivityUseCase =
        SaveActivityUseCase(
            activityRepository
        )

    val updateActivityUseCase =
        UpdateActivityUseCase(
            activityRepository
        )

    val deleteActivityUseCase =
        DeleteActivityUseCase(
            activityRepository
        )

    // Connections

    val getGoalConnectionsUseCase =
        GetGoalConnectionsUseCase(
            goalConnectionRepository
        )

    val saveGoalConnectionUseCase =
        SaveGoalConnectionUseCase(
            goalConnectionRepository
        )

    val deleteGoalConnectionUseCase =
        DeleteGoalConnectionUseCase(
            goalConnectionRepository
        )

    // Progreso

    val getUserProgressUseCase =
        GetUserProgressUseCase(
            userProgressRepository
        )

    val getUserLevelUseCase =
        GetUserLevelUseCase()

    val getLevelProgressUseCase =
        GetLevelProgressUseCase()


    // Achievements

    val getAchievementsUseCase =
        GetAchievementsUseCase(
            achievementRepository
        )

    val getAchievementByIdUseCase =
        GetAchievementByIdUseCase(
            achievementRepository
        )


    // Casos de uso compuestos

    val completeActivityUseCase =
        CompleteActivityUseCase(
            updateActivityUseCase
        )

    val completeGoalNodeUseCase =
        CompleteGoalNodeUseCase(
            updateGoalNodeUseCase
        )

}