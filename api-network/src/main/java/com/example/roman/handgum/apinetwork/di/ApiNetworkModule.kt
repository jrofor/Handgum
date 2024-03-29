package com.example.roman.handgum.apinetwork.di


import com.example.roman.handgum.apinetwork.ApiKeyInterceptor
import com.example.roman.handgum.apinetwork.BuildConfig
import com.example.roman.handgum.apinetwork.api.Api
import com.example.roman.handgum.apinetwork.api.ApiWorkerImpl
import com.example.roman.handgum.commonentity.network.ApiWorker
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author rofor
 */
@Module
internal class ApiNetworkModule {

    @Provides
    @Singleton
    fun provideAppApiWorker(retrofit: Retrofit): ApiWorker {
        val appApi = retrofit.create(Api::class.java)
        return ApiWorkerImpl(appApi)
    }

    @Provides
    fun provideOkHttp(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor.create(BuildConfig.API_KEY))
            .connectTimeout(BuildConfig.TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(BuildConfig.TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(BuildConfig.TIMEOUT, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClient.addInterceptor(interceptor)
        }
        return okHttpClient.build()
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