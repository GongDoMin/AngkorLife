package com.unionmobile.angkorlife.domain.impl

import com.unionmobile.angkorlife.domain.model.VotedCandidate
import com.unionmobile.angkorlife.domain.repository.CandidateRepository
import com.unionmobile.angkorlife.domain.usecase.GetVotedCandidatesIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVotedCandidatesIdUseCaseImpl @Inject constructor(
    private val candidateRepository: CandidateRepository
): GetVotedCandidatesIdUseCase {
    override suspend operator fun invoke(): Flow<List<VotedCandidate>> =
        candidateRepository.getVotedCandidatesId()
}