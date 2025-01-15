package com.unionmobile.angkorlife.domain.usecase

import kotlinx.coroutines.flow.Flow

interface VoteUseCase {
    suspend operator fun invoke(candidateId: Int, updatedVoteCnt: Int) : Flow<Unit>
}