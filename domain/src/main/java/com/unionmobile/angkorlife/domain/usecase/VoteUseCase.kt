package com.unionmobile.angkorlife.domain.usecase

import kotlinx.coroutines.flow.Flow

interface VoteUseCase {
    suspend operator fun invoke(candidateId: Int, voteCount: Int) : Flow<Unit>
}