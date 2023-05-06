package com.example.roman.handgum.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roman.handgum.database.dao.RevDao
import com.example.roman.handgum.database.entity.ReviewEntity

/**
 * @author rofor
 */
@Database(
    version = BuildConfig.DB_VERSION,
    entities = [
        ReviewEntity::class]
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun revDao(): RevDao
}
