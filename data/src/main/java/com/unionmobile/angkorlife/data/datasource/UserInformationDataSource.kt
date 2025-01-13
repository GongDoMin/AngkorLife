package com.unionmobile.angkorlife.data.datasource

import kotlinx.coroutines.flow.Flow

interface UserInformationDataSource {
    fun getUserId(): String

    fun setUserId(userId: String)

    fun updateVotedCandidate(candidateId: Int)

    fun getVotedCandidates(): Flow<List<Int>>
}