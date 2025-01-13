package com.unionmobile.angkorlife.local.di

import com.unionmobile.angkorlife.data.datasource.UserInformationDataSource
import com.unionmobile.angkorlife.local.impl.UserInformationDataSourceImpl
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
    abstract fun bindUserInformationDataSource(userInformationDataSourceImpl: UserInformationDataSourceImpl) : UserInformationDataSource
}