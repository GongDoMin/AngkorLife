package com.unionmobile.angkorlife.remote.di

import com.unionmobile.angkorlife.data.datasource.CandidateRemoteDataSource
import com.unionmobile.angkorlife.remote.impl.CandidateRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {
    @Binds
    abstract fun bindCandidateRemoteDataSource(candidateRemoteDataSourceImpl: CandidateRemoteDataSourceImpl) : CandidateRemoteDataSource
}