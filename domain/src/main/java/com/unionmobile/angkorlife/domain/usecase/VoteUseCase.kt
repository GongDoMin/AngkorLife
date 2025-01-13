package com.unionmobile.angkorlife.domain.usecase

import kotlinx.coroutines.flow.Flow

interface VoteUseCase {
    operator fun invoke(candidateId: Int) : Flow<Unit>
}