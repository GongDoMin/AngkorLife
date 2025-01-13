package com.unionmobile.angkorlife.domain.impl

import com.unionmobile.angkorlife.domain.repository.CandidateRepository
import com.unionmobile.angkorlife.domain.repository.UserIdRepository
import com.unionmobile.angkorlife.domain.repository.VotedCandidateRepository
import com.unionmobile.angkorlife.domain.usecase.VoteUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class VoteUseCaseImpl @Inject constructor(
    private val candidateRepository: CandidateRepository,
    private val userIdRepository: UserIdRepository,
    private val votedCandidateRepository: VotedCandidateRepository
) : VoteUseCase {
    override suspend operator fun invoke(candidateId: Int): Flow<Unit>{
        val userId = userIdRepository.getUserId()
        return candidateRepository.vote(userId, candidateId).onEach { votedCandidateRepository.updateVotedCandidate(candidateId) }
    }
}