package com.unionmobile.angkorlife.domain.repository

import kotlinx.coroutines.flow.Flow

interface VotedCandidateRepository {
    fun updateVotedCandidate(candidateId: Int)

    fun getVotedCandidates(): Flow<List<Int>>
}