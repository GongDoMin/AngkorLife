package com.unionmobile.angkorlife.domain.impl

import com.unionmobile.angkorlife.domain.repository.CandidateRepository
import com.unionmobile.angkorlife.domain.repository.UserIdRepository
import com.unionmobile.angkorlife.domain.usecase.GetVotedCandidatesIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVotedCandidatesIdUseCaseImpl @Inject constructor(
    private val candidateRepository: CandidateRepository,
    private val userIdRepository: UserIdRepository
): GetVotedCandidatesIdUseCase {
    override fun invoke(): Flow<List<Int>> {
        val userId = userIdRepository.getUserId()
        return candidateRepository.getVotedCandidatesId(userId)
    }
}