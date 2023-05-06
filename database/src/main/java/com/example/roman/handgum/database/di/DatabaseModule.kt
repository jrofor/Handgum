package com.example.roman.handgum.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roman.handgum.database.AppDatabase
import com.example.roman.handgum.database.BuildConfig
import dagger.Module
import dagger.Provides

@Module
interface DatabaseModule {
    companion object {
        @Provides
        fun appDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.DB_FILE_NAME)
                .addMigrations(Migration1to2)
                .build()
    }
}

object Migration1to2 : Migration(1, 2) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Review ADD COLUMN byline TEXT NOT NULL DEFAULT ''")
    }
}