package com.unionmobile.angkorlife.local.impl

import com.unionmobile.angkorlife.data.datasource.UserInformationLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class UserInformationLocalDataSourceImpl @Inject constructor(): UserInformationLocalDataSource {
    private var userId: String = ""
    private val votedCandidates = MutableStateFlow<List<Int>>(emptyList())

    override fun getUserId(): String =
        userId

    override fun setUserId(userId: String) {
        this.userId = userId
    }

    override fun updateVotedCandidate(candidateId: Int) {
        votedCandidates.update { currentList ->
            if (candidateId !in currentList) {
                currentList + candidateId
            } else {
                currentList
            }
        }
    }

    override fun getVotedCandidates(): Flow<List<Int>> =
        votedCandidates
}