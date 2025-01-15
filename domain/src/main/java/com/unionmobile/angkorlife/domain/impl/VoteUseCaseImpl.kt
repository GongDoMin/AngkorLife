package com.unionmobile.angkorlife.domain.impl

import com.unionmobile.angkorlife.domain.model.VotedCandidate
import com.unionmobile.angkorlife.domain.repository.CandidateRepository
import com.unionmobile.angkorlife.domain.usecase.VoteUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VoteUseCaseImpl @Inject constructor(
    private val candidateRepository: CandidateRepository,
) : VoteUseCase {
    override suspend operator fun invoke(candidateId: Int, voteCount: Int): Flow<Unit> =
        candidateRepository.vote(VotedCandidate(candidateId, voteCount))
}