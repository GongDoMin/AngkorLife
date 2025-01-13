package com.unionmobile.angkorlife.domain.usecase

import kotlinx.coroutines.flow.Flow

interface GetVotedCandidatesIdUseCase {
    suspend operator fun invoke(): Flow<List<Int>>
}