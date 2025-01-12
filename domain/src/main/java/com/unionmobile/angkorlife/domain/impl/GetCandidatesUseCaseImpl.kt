package com.unionmobile.angkorlife.domain.impl

import com.unionmobile.angkorlife.domain.model.Candidate
import com.unionmobile.angkorlife.domain.repository.CandidateRepository
import com.unionmobile.angkorlife.domain.usecase.GetCandidatesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCandidatesUseCaseImpl @Inject constructor(
    private val candidateRepository: CandidateRepository
): GetCandidatesUseCase {
    override fun invoke(): Flow<List<Candidate>> =
        candidateRepository.getCandidates(1, 100, listOf(NAME_ASC))
}

const val VOTE_COUNT_DESC = "voteCnt,DESC"
const val VOTE_COUNT_ASC = "voteCnt,ASC"
const val NAME_DESC = "name,DESC"
const val NAME_ASC = "name,ASC"
const val CANDIDATE_NUMBER_DESC = "candidateNumber,DESC"
const val CANDIDATE_NUMBER_ASC = "candidateNumber,ASC"