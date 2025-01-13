package com.unionmobile.angkorlife.data.di

import com.unionmobile.angkorlife.data.impl.CandidateRepositoryImpl
import com.unionmobile.angkorlife.data.impl.UserIdRepositoryImpl
import com.unionmobile.angkorlife.data.impl.VotedCandidateRepositoryImpl
import com.unionmobile.angkorlife.domain.repository.CandidateRepository
import com.unionmobile.angkorlife.domain.repository.UserIdRepository
import com.unionmobile.angkorlife.domain.repository.VotedCandidateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindUserIdRepository(userIdRepositoryImpl: UserIdRepositoryImpl): UserIdRepository

    @Binds
    @Singleton
    abstract fun bindCandidateRepository(candidateRepositoryImpl: CandidateRepositoryImpl): CandidateRepository

    @Binds
    @Singleton
    abstract fun bindVotedCandidateRepository(votedCandidateRepositoryImpl: VotedCandidateRepositoryImpl): VotedCandidateRepository

}