package com.example.roman.handgum.di

import android.content.Context
import androidx.room.Room
import com.example.roman.handgum.BuildConfig
import com.example.roman.handgum.data.db.AppDatabase
import com.example.roman.handgum.data.networkApi.api.Api
import com.example.roman.handgum.data.networkApi.api.ApiWorker
import com.example.roman.handgum.data.networkApi.api.ApiWorkerImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author rofor
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun appDatabase(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.DB_FILE_NAME)
            .build()

    @Provides
    @Singleton
    fun provideAppApiWorker(retrofit: Retrofit): ApiWorker {
        val appApi = retrofit.create(Api::class.java)
        return ApiWorkerImpl(appApi)
    }

    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            //.addInterceptor(LoggingInterceptor()) TODO: Add Interceptor for Debug
            .connectTimeout(BuildConfig.TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(BuildConfig.TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(BuildConfig.TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    fun provideRetroFit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

}