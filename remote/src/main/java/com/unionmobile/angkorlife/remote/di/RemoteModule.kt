package com.unionmobile.angkorlife.remote.di

import com.unionmobile.angkorlife.data.datasource.CandidateDataSource
import com.unionmobile.angkorlife.remote.impl.CandidateDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {
    @Binds
    abstract fun bindCandidateDataSource(candidateDataSourceImpl: CandidateDataSourceImpl) : CandidateDataSource
}