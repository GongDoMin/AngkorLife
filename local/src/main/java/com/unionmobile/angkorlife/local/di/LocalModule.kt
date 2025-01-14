package com.unionmobile.angkorlife.local.di

import com.unionmobile.angkorlife.data.datasource.UserInformationLocalDataSource
import com.unionmobile.angkorlife.local.impl.UserInformationLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModule {
    @Binds
    @Singleton
    abstract fun bindUserInformationLocalDataSource(userInformationLocalDataSourceImpl: UserInformationLocalDataSourceImpl) : UserInformationLocalDataSource
}