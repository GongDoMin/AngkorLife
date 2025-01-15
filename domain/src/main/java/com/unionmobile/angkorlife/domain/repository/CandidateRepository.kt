package com.unionmobile.angkorlife.domain.repository

import com.unionmobile.angkorlife.domain.model.Candidate
import com.unionmobile.angkorlife.domain.model.CandidateDetail
import com.unionmobile.angkorlife.domain.model.VotedCandidate
import kotlinx.coroutines.flow.Flow

interface CandidateRepository {
    fun getCandidates(page: Int, size: Int, sort: List<String>) : Flow<List<Candidate>>

    fun getCandidate(candidateId: Int) : Flow<CandidateDetail>

    suspend fun getVotedCandidatesId() : Flow<List<VotedCandidate>>

    fun vote(votedCandidate: VotedCandidate) : Flow<Unit>
}