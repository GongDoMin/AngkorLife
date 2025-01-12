package com.unionmobile.angkorlife.domain.usecase

import com.unionmobile.angkorlife.domain.model.Candidate
import kotlinx.coroutines.flow.Flow

interface GetCandidatesUseCase {
    operator fun invoke(candidateId: Int) : Flow<List<Candidate>>
}