package com.unionmobile.angkorlife.remote.di

import com.unionmobile.angkorlife.remote.service.AngkorLifeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {
    @Provides
    @Singleton
    fun provideGitHubAuthService(
        retrofit: Retrofit
    ): AngkorLifeService =
        retrofit.create(AngkorLifeService::class.java)
}