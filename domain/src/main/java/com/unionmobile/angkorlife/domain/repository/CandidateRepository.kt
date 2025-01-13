package com.unionmobile.angkorlife.domain.repository

import com.unionmobile.angkorlife.domain.model.Candidate
import com.unionmobile.angkorlife.domain.model.CandidateDetail
import kotlinx.coroutines.flow.Flow

interface CandidateRepository {
    fun getCandidates(page: Int, size: Int, sort: List<String>) : Flow<List<Candidate>>

    fun getCandidate(candidateId: Int, userId: String) : Flow<CandidateDetail>

    fun getVotedCandidatesId(userId: String) : Flow<List<Int>>

    fun vote(userId: String, candidateId: Int) : Flow<Unit>
}