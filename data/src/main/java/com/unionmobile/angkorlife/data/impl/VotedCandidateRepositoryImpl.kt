package com.unionmobile.angkorlife.data.impl

import com.unionmobile.angkorlife.domain.repository.VotedCandidateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class VotedCandidateRepositoryImpl @Inject constructor(): VotedCandidateRepository {

    private val votedCandidates = MutableStateFlow<List<Int>>(emptyList())

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