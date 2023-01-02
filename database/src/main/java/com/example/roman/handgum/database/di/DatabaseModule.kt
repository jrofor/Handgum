package com.example.roman.handgum.database.di

import android.content.Context
import androidx.room.Room
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
                .build()
    }
}