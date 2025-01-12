package com.unionmobile.angkorlife.data.impl

import com.unionmobile.angkorlife.data.datasource.CandidateDataSource
import com.unionmobile.angkorlife.data.model.toModel
import com.unionmobile.angkorlife.domain.model.Candidate
import com.unionmobile.angkorlife.domain.model.CandidateDetail
import com.unionmobile.angkorlife.domain.repository.CandidateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CandidateRepositoryImpl @Inject constructor(
    private val candidateDataSource: CandidateDataSource
): CandidateRepository {
    override fun getCandidates(page: Int, size: Int, sort: List<String>): Flow<List<Candidate>> =
        candidateDataSource.getCandidates(page, size, sort).map { it.map { it.toModel() } }

    override fun getCandidate(candidateId: Int, userId: String): Flow<CandidateDetail> =
        candidateDataSource.getCandidate(candidateId, userId).map { it.toModel() }

    override fun getVotedCandidatesId(userId: String): Flow<List<Int>> =
        candidateDataSource.getVotedCandidatesId(userId)
}