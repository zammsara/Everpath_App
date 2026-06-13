package com.everpath.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.everpath.data.local.dao.GoalDao
import com.everpath.data.local.entity.GoalEntity

@Database(
    entities = [
        GoalEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class EverpathDatabase : RoomDatabase() {

    abstract fun goalDao(): GoalDao

}