package com.unionmobile.angkorlife.domain.di

import com.unionmobile.angkorlife.domain.core.time.DefaultTimerProvider
import com.unionmobile.angkorlife.domain.core.time.TimerProvider
import com.unionmobile.angkorlife.domain.impl.GetCandidateDetailUseCaseImpl
import com.unionmobile.angkorlife.domain.impl.GetCandidatesUseCaseImpl
import com.unionmobile.angkorlife.domain.impl.GetTimerUseCaseImpl
import com.unionmobile.angkorlife.domain.impl.GetVotedCandidatesIdUseCaseImpl
import com.unionmobile.angkorlife.domain.impl.LoginUseCaseImpl
import com.unionmobile.angkorlife.domain.impl.VoteUseCaseImpl
import com.unionmobile.angkorlife.domain.usecase.GetCandidateDetailUseCase
import com.unionmobile.angkorlife.domain.usecase.GetCandidatesUseCase
import com.unionmobile.angkorlife.domain.usecase.GetTimerUseCase
import com.unionmobile.angkorlife.domain.usecase.GetVotedCandidatesIdUseCase
import com.unionmobile.angkorlife.domain.usecase.LoginUseCase
import com.unionmobile.angkorlife.domain.usecase.VoteUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindTimerProvider(defaultTimerProvider: DefaultTimerProvider) : TimerProvider

    @Binds
    abstract fun bindLoginUseCase(loginUseCaseImpl: LoginUseCaseImpl) : LoginUseCase

    @Binds
    abstract fun bindGetTimerUseCase(getTimerUseCaseImpl: GetTimerUseCaseImpl) : GetTimerUseCase

    @Binds
    abstract fun bindGetCandidatesUseCase(getCandidatesUseCaseImpl: GetCandidatesUseCaseImpl) : GetCandidatesUseCase

    @Binds
    abstract fun bindGetVotedCandidatesIdUseCase(getVotedCandidatesIdUseCaseImpl: GetVotedCandidatesIdUseCaseImpl) : GetVotedCandidatesIdUseCase

    @Binds
    abstract fun bindGetCandidateDetailUseCase(getCandidateDetailUseCaseImpl: GetCandidateDetailUseCaseImpl) : GetCandidateDetailUseCase

    @Binds
    abstract fun bindVoteUseCase(voteUseCaseImpl: VoteUseCaseImpl) : VoteUseCase

}