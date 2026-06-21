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
import com.everpath.domain.usecase.achievement.EvaluateAchievementsUseCase
import com.everpath.domain.usecase.achievement.GetAchievementByIdUseCase
import com.everpath.domain.usecase.achievement.GetAchievementsUseCase
import com.everpath.domain.usecase.achievement.SaveAchievementUseCase
import com.everpath.domain.usecase.userprogress.GetUserProgressUseCase
import com.everpath.domain.usecase.userprogress.SaveUserProgressUseCase
import com.everpath.domain.usecase.userprogress.UpdateUserProgressUseCase
import com.everpath.domain.usecase.userprogress.AddXpUseCase
import com.everpath.domain.usecase.activity.CompleteActivityUseCase
import com.everpath.domain.usecase.goal.CompleteGoalNodeUseCase
import com.everpath.domain.usecase.userprogress.GetLevelProgressUseCase
import com.everpath.domain.usecase.userprogress.GetUserLevelUseCase


/**
 * Contenedor principal de dependencias de Everpath.
 *
 * Centraliza la creación de Room, repositorios
 * y casos de uso de la aplicación.
 */
class AppContainer(
    context: Context
) {

    private val database: EverpathDatabase =
        Room.databaseBuilder(
            context,
            EverpathDatabase::class.java,
            "everpath_database"
        )
            .fallbackToDestructiveMigration()
            .build()

    private val goalRepository: GoalRepository =
        GoalRepositoryImpl(
            goalDao = database.goalDao()
        )

    private val activityRepository:
            ActivityRepository =

        ActivityRepositoryImpl(
            activityDao =
                database.activityDao()
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
                database.userProgressDao()
        )

    private val achievementRepository:
            AchievementRepository =

        AchievementRepositoryImpl(
            achievementDao =
                database.achievementDao()
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

    val updateGoalPositionUseCase =
        UpdateGoalPositionUseCase(
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

    //PROGRESO DEL USUARIO
    val getUserProgressUseCase =
        GetUserProgressUseCase(
            userProgressRepository
        )

    val saveUserProgressUseCase =
        SaveUserProgressUseCase(
            userProgressRepository
        )

    val updateUserProgressUseCase =
        UpdateUserProgressUseCase(
            userProgressRepository
        )

    val getUserLevelUseCase =
        GetUserLevelUseCase()

    val getLevelProgressUseCase =
        GetLevelProgressUseCase()

    //Achievements
    val getAchievementsUseCase =
        GetAchievementsUseCase(
            achievementRepository
        )

    val saveAchievementUseCase =
        SaveAchievementUseCase(
            achievementRepository
        )

    val getAchievementByIdUseCase =
        GetAchievementByIdUseCase(
            achievementRepository
        )

    val evaluateAchievementsUseCase =
        EvaluateAchievementsUseCase()

    //Agregar XP al usuario
    val addXpUseCase =
        AddXpUseCase(
            userProgressRepository
        )

    val completeActivityUseCase =
        CompleteActivityUseCase(
            updateActivityUseCase,
            addXpUseCase
        )

    val completeGoalNodeUseCase =
        CompleteGoalNodeUseCase(
            updateGoalNodeUseCase,
            addXpUseCase
        )
}