package com.unionmobile.angkorlife.domain.impl

import com.unionmobile.angkorlife.domain.repository.CandidateRepository
import com.unionmobile.angkorlife.domain.repository.UserIdRepository
import com.unionmobile.angkorlife.domain.usecase.VoteUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VoteUseCaseImpl @Inject constructor(
    private val candidateRepository: CandidateRepository,
    private val userIdRepository: UserIdRepository
) : VoteUseCase {
    override fun invoke(candidateId: Int): Flow<Unit>{
        val userId = userIdRepository.getUserId()
        return candidateRepository.vote(userId, candidateId)
    }
}