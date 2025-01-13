package com.unionmobile.angkorlife.data.datasource

import com.unionmobile.angkorlife.data.model.CandidateDetailEntity
import com.unionmobile.angkorlife.data.model.CandidateEntity
import kotlinx.coroutines.flow.Flow

interface CandidateDataSource {
    fun getCandidates(page: Int, size: Int, sort: List<String>) : Flow<List<CandidateEntity>>

    fun getCandidate(candidateId: Int, userId: String) : Flow<CandidateDetailEntity>

    fun getVotedCandidatesId(userId: String) : Flow<List<Int>>

    fun vote(userId: String, candidateId: Int) : Flow<Unit>
}