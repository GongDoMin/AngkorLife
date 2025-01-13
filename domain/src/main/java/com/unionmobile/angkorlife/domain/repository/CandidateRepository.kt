package com.unionmobile.angkorlife.domain.repository

import com.unionmobile.angkorlife.domain.model.Candidate
import com.unionmobile.angkorlife.domain.model.CandidateDetail
import kotlinx.coroutines.flow.Flow

interface CandidateRepository {
    fun getCandidates(page: Int, size: Int, sort: List<String>) : Flow<List<Candidate>>

    fun getCandidate(candidateId: Int) : Flow<CandidateDetail>

    suspend fun getVotedCandidatesId() : Flow<List<Int>>

    fun vote(candidateId: Int) : Flow<Unit>
}