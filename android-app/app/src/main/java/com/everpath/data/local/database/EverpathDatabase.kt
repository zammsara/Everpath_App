package com.everpath.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.everpath.data.local.dao.ActivityDao
import com.everpath.data.local.dao.GoalDao
import com.everpath.data.local.dao.GoalPositionDao
import com.everpath.data.local.entity.ActivityEntity
import com.everpath.data.local.entity.GoalEntity
import com.everpath.data.local.entity.GoalPositionEntity
import com.everpath.data.local.dao.GoalConnectionDao
import com.everpath.data.local.dao.UserProgressDao
import com.everpath.data.local.entity.GoalConnectionEntity
import com.everpath.data.local.entity.UserProgressEntity

@Database(
    entities = [
        GoalEntity::class,
        ActivityEntity::class,
        GoalPositionEntity::class,
        GoalConnectionEntity::class,
        UserProgressEntity::class
    ],
    version = 5,
    exportSchema = false
)
abstract class EverpathDatabase : RoomDatabase() {

    abstract fun goalDao(): GoalDao

    abstract fun activityDao(): ActivityDao

    abstract fun goalPositionDao(): GoalPositionDao

    abstract fun goalConnectionDao(): GoalConnectionDao

    abstract fun userProgressDao(): UserProgressDao
}