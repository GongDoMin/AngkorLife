package com.unionmobile.angkorlife.data.datasource

import com.unionmobile.angkorlife.data.model.CandidateDetailEntity
import com.unionmobile.angkorlife.data.model.CandidateEntity

interface CandidateRemoteDataSource {
    suspend fun getCandidates(page: Int, size: Int, sort: List<String>) : List<CandidateEntity>

    suspend fun getCandidate(candidateId: Int, userId: String) : CandidateDetailEntity

    suspend fun getVotedCandidatesId(userId: String) : List<Int>

    suspend fun vote(candidateId: Int, userId: String)
}