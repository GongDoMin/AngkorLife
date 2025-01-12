package com.unionmobile.angkorlife.remote.di

import com.unionmobile.angkorlife.remote.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class OkHttpClientModule {
    @Provides
    fun provideOkHttpClient() : OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(timeout = 10, unit = TimeUnit.SECONDS)
            .readTimeout(timeout = 10, unit = TimeUnit.SECONDS)
            .writeTimeout(timeout = 10, unit = TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC else HttpLoggingInterceptor.Level.NONE
                }
            )
            .build()
}