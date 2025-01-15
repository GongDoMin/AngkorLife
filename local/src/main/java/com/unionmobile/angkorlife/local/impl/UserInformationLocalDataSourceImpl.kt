package com.unionmobile.angkorlife.local.impl

import com.unionmobile.angkorlife.data.datasource.UserInformationLocalDataSource
import com.unionmobile.angkorlife.data.model.VotedCandidateEntity
import com.unionmobile.angkorlife.local.model.VotedCandidateLocal
import com.unionmobile.angkorlife.local.model.toEntity
import com.unionmobile.angkorlife.local.model.toLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class UserInformationLocalDataSourceImpl @Inject constructor(): UserInformationLocalDataSource {
    private var userId: String = ""
    private val votedCandidates = MutableStateFlow<List<VotedCandidateLocal>>(emptyList())

    override fun getUserId(): String =
        userId

    override fun setUserId(userId: String) {
        this.userId = userId
    }

    override fun updateVotedCandidate(votedCandidate: VotedCandidateEntity) {
        val local = votedCandidate.toLocal()
        votedCandidates.update { currentList ->
            if (currentList.none { it.candidateId == local.candidateId }) {
                currentList + local
            } else {
                currentList
            }
        }
    }

    override fun getVotedCandidates(): Flow<List<VotedCandidateEntity>> =
        votedCandidates.map { it.map { it.toEntity() } }
}