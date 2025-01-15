package com.unionmobile.angkorlife.domain.usecase

import com.unionmobile.angkorlife.domain.model.VotedCandidate
import kotlinx.coroutines.flow.Flow

interface GetVotedCandidatesIdUseCase {
    suspend operator fun invoke(): Flow<List<VotedCandidate>>
}