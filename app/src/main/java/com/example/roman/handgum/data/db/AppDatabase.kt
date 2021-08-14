package com.example.roman.handgum.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roman.handgum.BuildConfig
import com.example.roman.handgum.data.db.dao.RevDao
import com.example.roman.handgum.data.db.entity.ReviewEntity

/**
 * @author rofor
 */
@Database(
    exportSchema = false,
    version = BuildConfig.DB_VERSION,
    entities = [
        ReviewEntity::class]
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun revDao(): RevDao
}
