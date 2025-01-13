package com.unionmobile.angkorlife.domain.impl

import com.unionmobile.angkorlife.domain.model.CandidateDetail
import com.unionmobile.angkorlife.domain.repository.CandidateRepository
import com.unionmobile.angkorlife.domain.usecase.GetCandidateDetailUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCandidateDetailUseCaseImpl @Inject constructor(
    private val candidateRepository: CandidateRepository
): GetCandidateDetailUseCase {
    override fun invoke(candidateId: Int): Flow<CandidateDetail> {
        return candidateRepository.getCandidate(candidateId)
    }
}