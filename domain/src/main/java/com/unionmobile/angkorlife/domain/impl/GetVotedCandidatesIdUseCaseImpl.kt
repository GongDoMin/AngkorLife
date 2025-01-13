package com.unionmobile.angkorlife.domain.impl

import com.unionmobile.angkorlife.domain.repository.CandidateRepository
import com.unionmobile.angkorlife.domain.repository.UserIdRepository
import com.unionmobile.angkorlife.domain.repository.VotedCandidateRepository
import com.unionmobile.angkorlife.domain.usecase.GetVotedCandidatesIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class GetVotedCandidatesIdUseCaseImpl @Inject constructor(
    private val candidateRepository: CandidateRepository,
    private val userIdRepository: UserIdRepository,
    private val votedCandidateRepository: VotedCandidateRepository
): GetVotedCandidatesIdUseCase {
    override suspend operator fun invoke(): Flow<List<Int>> {
        val userId = userIdRepository.getUserId()
        candidateRepository
            .getVotedCandidatesId(userId)
            .catch {}
            .collect {
                it.forEach {
                    votedCandidateRepository.updateVotedCandidate(it)
                }
            }
        return votedCandidateRepository.getVotedCandidates()
    }
}