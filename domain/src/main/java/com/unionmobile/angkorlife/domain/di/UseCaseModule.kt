package com.unionmobile.angkorlife.domain.di

import com.unionmobile.angkorlife.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun provideLoginUseCase() : LoginUseCase =
        LoginUseCase()
}