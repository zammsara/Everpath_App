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
import com.everpath.data.local.entity.GoalConnectionEntity

@Database(
    entities = [
        GoalEntity::class,
        ActivityEntity::class,
        GoalPositionEntity::class,
        GoalConnectionEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class EverpathDatabase : RoomDatabase() {

    abstract fun goalDao(): GoalDao

    abstract fun activityDao(): ActivityDao

    abstract fun goalPositionDao(): GoalPositionDao
}