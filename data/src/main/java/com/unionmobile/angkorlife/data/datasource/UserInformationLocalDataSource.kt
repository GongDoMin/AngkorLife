package com.unionmobile.angkorlife.data.datasource

import com.unionmobile.angkorlife.data.model.VotedCandidateEntity
import kotlinx.coroutines.flow.Flow

interface UserInformationLocalDataSource {
    fun getUserId(): String

    fun setUserId(userId: String)

    fun updateVotedCandidate(votedCandidate: VotedCandidateEntity)

    fun getVotedCandidates(): Flow<List<VotedCandidateEntity>>
}