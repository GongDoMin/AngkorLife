package com.unionmobile.angkorlife.domain.usecase

import com.unionmobile.angkorlife.domain.model.Candidate
import kotlinx.coroutines.flow.Flow

interface GetCandidatesUseCase {
    fun invoke() : Flow<List<Candidate>>
}