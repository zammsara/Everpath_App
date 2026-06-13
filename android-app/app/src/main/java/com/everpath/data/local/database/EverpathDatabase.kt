package com.everpath.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.everpath.data.local.dao.ActivityDao
import com.everpath.data.local.dao.GoalDao
import com.everpath.data.local.entity.ActivityEntity
import com.everpath.data.local.entity.GoalEntity

@Database(
    entities = [
        GoalEntity::class,
        ActivityEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class EverpathDatabase : RoomDatabase() {

    abstract fun goalDao(): GoalDao

    abstract fun activityDao(): ActivityDao
}